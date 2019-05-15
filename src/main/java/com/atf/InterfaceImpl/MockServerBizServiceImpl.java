package com.atf.InterfaceImpl;

import com.atf.Interface.*;
import com.atf.dto.BaseRespDto;
import com.atf.dto.CommonKeysToValues;
import com.atf.dto.ExpectationReqDto;
import com.atf.dto.HttpResponseRespDto;
import com.atf.entity.ConnectionOptionsEntity;
import com.atf.entity.ExpectationEntity;
import com.atf.entity.HttpRequestEntity;
import com.atf.entity.HttpResponseEntity;
import com.google.common.net.MediaType;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.mockserver.client.MockServerClient;
import org.mockserver.client.NettyHttpClient;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.matchers.MatchType;
import org.mockserver.mock.Expectation;
import org.mockserver.mock.HttpStateHandler;
import org.mockserver.mock.action.HttpForwardActionHandler;
import org.mockserver.mock.action.HttpForwardActionResult;
import org.mockserver.model.*;
import org.mockserver.scheduler.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.HttpStatusCode.BAD_REQUEST_400;
import static org.mockserver.model.HttpStatusCode.NOT_FOUND_404;

@Service
@Slf4j
public class MockServerBizServiceImpl implements MockServerBizService {
    @Autowired
    HttpRequestService httpRequestService;

    @Autowired
    HttpResponseService httpResponseService;

    @Autowired
    ConnectionOptionsService connectionOptionsService;

    @Autowired
    ExpectationService expectationService;
    @Autowired
    HttpForwardService httpForwardService;

    private HttpStateHandler httpStateHandler = new HttpStateHandler(new Scheduler());
    private final Scheduler scheduler = httpStateHandler.getScheduler();
    /*NettyHttpClient httpClient = new NettyHttpClient(new NioEventLoopGroup(), null);*/
    HttpForwardActionHandler httpForwardActionHandler = new HttpForwardActionHandler(httpStateHandler.getMockServerLogger(), new NettyHttpClient(new NioEventLoopGroup(), null));
    private MockServerClient mockServer;
    private String mockHost;
    private Integer mockPort;

    @PostConstruct
    public void postConstruct(){
            mockPort = 1088;
            mockHost = "localhost";
            //mockServer = new MockServerClient(mockHost,mockPort);//发送request到远程的mockserver实例
            mockServer = startClientAndServer(1088);
            try{
                initMockServerExpectations();
            } catch (Exception e){
                throw new RuntimeException("启动mockserver出现异常");
            }
    }

    @PreDestroy
    public void stopMockServer() {
            mockServer.stop();
    }

    public ExpectationReqDto addExpectationClient(ExpectationReqDto e){
        ExpectationReqDto expectationReqDto = new ExpectationReqDto();
        ExpectationEntity expectationEntity = new ExpectationEntity();
        expectationEntity.setCreator(e.getCreator());
        expectationEntity.setExpectationName(e.getExpectationName());
        expectationEntity.setType(e.getType());
        expectationEntity.setCreateTime(new Date());
        expectationEntity.setModifiedTime(new Date());
        if(e.getHttpRequest() != null){
            httpRequestService.insert(e.getHttpRequest());
            expectationEntity.setHttpRequestId(e.getHttpRequest().getId());
            expectationReqDto.setHttpRequest(e.getHttpRequest());
        }
        if(ExpectationReqDto.Action.FORWARD.getName().equalsIgnoreCase(e.getType())){
                httpForwardService.insertSelective(e.getHttpForwardEntity());
                expectationEntity.setHttpForwardId(e.getHttpForwardEntity().getId());
                expectationReqDto.setHttpForwardEntity(e.getHttpForwardEntity());
        }else{
            if(e.getHttpResponse().getConnectionOptionsEntity() != null){
                connectionOptionsService.insert(e.getHttpResponse().getConnectionOptionsEntity());
                e.getHttpResponse().setConnectionOptionsId(e.getHttpResponse().getConnectionOptionsEntity().getId());
            }
            if(e.getHttpResponse() != null){
                httpResponseService.insert(e.getHttpResponse());
                expectationEntity.setHttpResponseId(e.getHttpResponse().getId());
            }
            expectationReqDto.setHttpResponse(e.getHttpResponse());
        }
        addExpectationServer(e);
        expectationService.insert(expectationEntity);
        BeanUtils.copyProperties(expectationEntity,expectationReqDto);
        expectationReqDto.setId(expectationEntity.getId());
        return expectationReqDto;
    }

    public void addBaseInfo(HashMap map) throws Exception{
        expectationService.insertBaseInfo(map);
    }

    public List<ExpectationReqDto> getAllExpectation() throws Exception {
        List<ExpectationReqDto> expectationRespList = new ArrayList<>();
        List<ExpectationEntity> expectationEntityList = expectationService.selectAll();
        for(ExpectationEntity expectationEntity:expectationEntityList){
            ExpectationReqDto expectationReqDto = new ExpectationReqDto();
            expectationReqDto.setId(expectationEntity.getId());
            expectationReqDto.setCreator(expectationEntity.getCreator());
            expectationReqDto.setExpectationName(expectationEntity.getExpectationName());
            expectationReqDto.setCreateTime(expectationEntity.getCreateTime());
            expectationReqDto.setModifiedTime(expectationEntity.getModifiedTime());
            expectationReqDto.setType(expectationEntity.getType());
            Integer httpRequestId = expectationEntity.getHttpRequestId();
            Integer httpResponseId = expectationEntity.getHttpResponseId();
            Integer httpForwardId = expectationEntity.getHttpForwardId();
            if(httpRequestId != null)
                expectationReqDto.setHttpRequest(httpRequestService.selectByPrimaryKeyId(httpRequestId));
            if(httpResponseId != null){
                expectationReqDto.setHttpResponse(httpResponseService.selectByPrimaryKeyId(httpResponseId));
                Integer connectOptId = expectationReqDto.getHttpResponse().getConnectionOptionsId();
                if(connectOptId != null) expectationReqDto.getHttpResponse().setConnectionOptionsEntity(connectionOptionsService.selectByPrimaryKeyId(connectOptId));
            }
            if(httpForwardId != null){
                expectationReqDto.setHttpForwardEntity(httpForwardService.selectByPrimaryKeyId(httpForwardId));
            }
            expectationRespList.add(expectationReqDto);
        }
        return expectationRespList;
    }

    public void initMockServerExpectations() throws Exception{
        List<ExpectationReqDto> expectationRespList = getAllExpectation();
        for(ExpectationReqDto expectationReqDto : expectationRespList){
            addExpectationServer(expectationReqDto);
        }
    }

    public BaseRespDto delExpectationClient(Integer id) throws Exception{
        ExpectationEntity expectationEntity = expectationService.selectByPrimaryKeyId(id);
        HttpRequestEntity httpRequestEntity = httpRequestService.selectByPrimaryKeyId(expectationEntity.getHttpRequestId());
        delExpectationServer(httpRequestEntity);
        if(expectationEntity.getHttpRequestId() != null){
            httpRequestService.deleteByPrimaryKeyId(expectationEntity.getHttpRequestId());
        }
        if(expectationEntity.getHttpResponseId() != null){
            HttpResponseEntity responseEntity = httpResponseService.selectByPrimaryKeyId(expectationEntity.getHttpResponseId());
            httpResponseService.deleteByPrimaryKeyId(expectationEntity.getHttpResponseId());
            if(responseEntity.getConnectionOptionsId() != null) connectionOptionsService.deleteByPrimaryKeyId(responseEntity.getConnectionOptionsId());
        }
        if(expectationEntity.getHttpForwardId() != null){
            httpForwardService.deleteByPrimaryKeyId(expectationEntity.getHttpForwardId());
        }
        expectationService.deleteByPrimaryKeyId(id);
        return BaseRespDto.success("删除成功");
    }

    public ExpectationReqDto updateExpectationClient(ExpectationReqDto e) throws Exception {
        //删库
        ExpectationReqDto expectationReqDto = new ExpectationReqDto();
        ExpectationEntity expectationEntity = new ExpectationEntity();
        //更新expectation
        expectationEntity.setId(e.getId());
        expectationEntity.setModifiedTime(new Date());
        expectationEntity.setCreateTime(e.getCreateTime());
        expectationEntity.setCreator(e.getCreator());
        expectationEntity.setHttpRequestId(e.getHttpRequest().getId());
        expectationEntity.setExpectationName(e.getExpectationName());
        expectationEntity.setType(e.getType());
        //删除request 更新数据库
        delExpectationServer(httpRequestService.selectByPrimaryKeyId(e.getHttpRequest().getId()));
        httpRequestService.updateByPrimaryKeySelective(e.getHttpRequest());
        if(e.getType().equalsIgnoreCase(ExpectationReqDto.Action.FORWARD.getName())){
            httpForwardService.updateByPrimaryKeySelective(e.getHttpForwardEntity());
            expectationEntity.setHttpForwardId(e.getHttpForwardEntity().getId());
        }else{
            expectationEntity.setHttpResponseId(e.getHttpResponse().getId());
            httpResponseService.updateByPrimaryKeySelective(e.getHttpResponse());
            if(e.getHttpResponse().getConnectionOptionsId() != null)
                connectionOptionsService.updateByPrimaryKeySelective(e.getHttpResponse().getConnectionOptionsEntity());
        }
        expectationService.updateByPrimaryKeySelective(expectationEntity);
        //添加修改后的Expectation
        addExpectationServer(e);
        BeanUtils.copyProperties(e,expectationReqDto);
        expectationReqDto.setModifiedTime(new Date());
        return expectationReqDto;
    }

    public ExpectationReqDto getExpectationById(Integer id) throws Exception{
        ExpectationReqDto expectationReqDto = new ExpectationReqDto();
        ExpectationEntity expectationEntity = expectationService.selectByPrimaryKeyId(id);
        expectationReqDto.setId(expectationEntity.getId());
        expectationReqDto.setCreator(expectationEntity.getCreator());
        expectationReqDto.setExpectationName(expectationEntity.getExpectationName());
        expectationReqDto.setCreateTime(expectationEntity.getCreateTime());
        expectationReqDto.setModifiedTime(expectationEntity.getModifiedTime());
        expectationReqDto.setType(expectationEntity.getType());
        Integer httpRequestId = expectationEntity.getHttpRequestId();
        Integer httpResponseId = expectationEntity.getHttpResponseId();
        Integer httpForwardId = expectationEntity.getHttpForwardId();
        if(httpRequestId != null)
            expectationReqDto.setHttpRequest(httpRequestService.selectByPrimaryKeyId(httpRequestId));
        if(httpResponseId != null){
            expectationReqDto.setHttpResponse(httpResponseService.selectByPrimaryKeyId(httpResponseId));
            Integer connectOptId = expectationReqDto.getHttpResponse().getConnectionOptionsId();
            if(connectOptId != null) expectationReqDto.getHttpResponse().setConnectionOptionsEntity(connectionOptionsService.selectByPrimaryKeyId(connectOptId));
        }
        if(httpForwardId != null) expectationReqDto.setHttpForwardEntity(httpForwardService.selectByPrimaryKeyId(httpForwardId));
        return expectationReqDto;
    }

    public HttpResponseRespDto runAction(HttpRequestEntity entity) throws Exception{
        HttpRequest httpRequest = HttpRequest.request();
        httpRequest.withBody(convertToBody(entity.getBody(),entity.getType()))
                .withCookies(convertToCookies(entity.getCookies()))
                .withPath(entity.getPath())
                .withMethod(entity.getMethod())
                .withHeaders(convertToHeaders(entity.getHeaders()))
                .withQueryStringParameters(convertToParameters(entity.getQueryParameters()))
                .withKeepAlive(entity.getKeepAlive())
                .withSecure(entity.getSecure());
        Expectation expectation = httpStateHandler.firstMatchingExpectation(httpRequest);
        //HttpResponseRespDto httpResponseRespDto = new HttpResponseRespDto();
        if (expectation != null && expectation.getAction() != null){
            final Action action = expectation.getAction();
            switch (action.getType()) {
                case RESPONSE: {
                    HttpResponseRespDto httpResponseRespDto = new HttpResponseRespDto();
                    scheduler.submit(()->{
                                HttpResponse httpResponse = expectation.getHttpResponse();
                                copyToResponseRespDto(httpResponse,httpResponseRespDto);
                            }
                            , false);
                    return httpResponseRespDto;
                }
                case FORWARD:{
                    HttpResponseRespDto httpResponseRespDto = new HttpResponseRespDto();
                    scheduler.schedule(()-> {
                        try {
                            httpRequest.withHeader("Host" ,mockHost+":"+mockPort);
                        HttpResponse httpResponse = httpForwardActionHandler.handle((HttpForward) action, httpRequest).getHttpResponse().get();
                            copyToResponseRespDto(httpResponse,httpResponseRespDto);
                        } catch (Exception ex) {
                            convertToErrorInf(BAD_REQUEST_400,String.format("error expectation for:{}",httpRequest),httpResponseRespDto);
                        }
                    }, false, action.getDelay());
                    return  httpResponseRespDto;
                }
            }
        }
        //找不到
            HttpResponseRespDto httpResponseRespDto = new HttpResponseRespDto();
            convertToErrorInf(NOT_FOUND_404,"Not found expectation",httpResponseRespDto);
            return httpResponseRespDto;
    }

    private void delExpectationServer(HttpRequestEntity entity){
        HttpRequest httpRequest = HttpRequest.request();
        httpRequest.withBody(convertToBody(entity.getBody(),entity.getType()))
                    .withCookies(convertToCookies(entity.getCookies()))
                    .withPath(entity.getPath())
                    .withMethod(entity.getMethod())
                    .withHeaders(convertToHeaders(entity.getHeaders()))
                    .withQueryStringParameters(convertToParameters(entity.getQueryParameters()))
                    .withKeepAlive(entity.getKeepAlive())
                    .withSecure(entity.getSecure());
        httpStateHandler.getMockServerMatcher().clear(httpRequest);
    }

    private void addExpectationServer(ExpectationReqDto expectationReqDto){
        HttpRequest httpRequest = request()
                .withMethod(expectationReqDto.getHttpRequest().getMethod())
                .withPath(expectationReqDto.getHttpRequest().getPath())
                .withBody(convertToBody(expectationReqDto.getHttpRequest().getBody(),expectationReqDto.getHttpRequest().getType()))
                .withQueryStringParameters(convertToParameters(expectationReqDto.getHttpRequest().getQueryParameters()))
                .withCookies(convertToCookies(expectationReqDto.getHttpRequest().getCookies()))
                .withHeaders(convertToHeaders(expectationReqDto.getHttpRequest().getHeaders()))
                .withKeepAlive(expectationReqDto.getHttpRequest().getKeepAlive())
                .withSecure(expectationReqDto.getHttpRequest().getSecure());
        Expectation expectation = new Expectation(httpRequest);
        switch (expectationReqDto.getType().toLowerCase()){
            case "forward":{
                HttpForward httpForward = HttpForward.forward()
                        .withPort(expectationReqDto.getHttpForwardEntity().getPort())
                        .withHost(expectationReqDto.getHttpForwardEntity().getHost())
                        .withScheme(expectationReqDto.getHttpForwardEntity().getScheme().equalsIgnoreCase("http")? HttpForward.Scheme.HTTP: HttpForward.Scheme.HTTPS);
                expectation.thenForward(httpForward);
                break;
            }
            case "response":{
                HttpResponse httpResponse = response()
                        .withStatusCode(expectationReqDto.getHttpResponse().getStatusCode())
                        .withBody(convertToBody(expectationReqDto.getHttpResponse().getBody(),expectationReqDto.getHttpRequest().getType()))
                        .withHeaders(convertToHeaders(expectationReqDto.getHttpResponse().getCookies()))
                        .withCookies(convertToCookies(expectationReqDto

                                .getHttpRequest().getCookies()))
                        .withReasonPhrase(expectationReqDto.getHttpResponse().getReasonPhrase())
                        .withConnectionOptions(convertToConnectionOpt(expectationReqDto.getHttpResponse().getConnectionOptionsEntity()))
                        .withDelay(TimeUnit.MILLISECONDS,expectationReqDto.getHttpResponse().getDelayTime());
                expectation.thenRespond(httpResponse);
                break;
            }
        }
        httpStateHandler.add(expectation);
    }



    private List<Parameter> convertToParameters(String queryParametersString) {
        if(queryParametersString == null || "".equals(queryParametersString)) return null;
        List<Parameter> parameterList = new ArrayList<>();
        Map<String, String> entityMap = (Map<String, String>) JSONObject.fromObject(queryParametersString);
        for(Map.Entry<String,String> entry : entityMap.entrySet()){
            Parameter parameter = new Parameter(entry.getKey(),entry.getValue());
            parameterList.add(parameter);
        }
        return parameterList;
    }

    private  List<Cookie> convertToCookies(String cookieString){
        if(cookieString == null || "".equals(cookieString)) return null;
        List<Cookie> cookieList = new ArrayList<>();
        Map<String, String> entityMap = (Map<String, String>) JSONObject.fromObject(cookieString);
        for(Map.Entry<String,String> entry : entityMap.entrySet()){
            Cookie cookie = new Cookie(entry.getKey(),entry.getValue());
            cookieList.add(cookie);
        }
        return cookieList;
    }

    private List<Header> convertToHeaders(String headerString){
        if(headerString == null || "".equals(headerString)) return null;
        Map<String, String> entityMap = (Map<String, String>) JSONObject.fromObject(headerString);
        List<Header> headerList = new ArrayList<>();
        for(Map.Entry<String,String> entry : entityMap.entrySet()){
            Header header = new Header(entry.getKey(),entry.getValue());
            headerList.add(header);
        }
        return headerList;
    }

    private ConnectionOptions convertToConnectionOpt(ConnectionOptionsEntity cpEntity){
        if(cpEntity == null) return null;
        ConnectionOptions cp = new ConnectionOptions();
        cp.withCloseSocket(cpEntity.getCloseSocket());
        cp.withContentLengthHeaderOverride(cpEntity.getContentLengthHeaderOverride());
        cp.withKeepAliveOverride(cpEntity.getKeepAliveOverride());
        cp.withSuppressConnectionHeader(cpEntity.getSuppressConnectionHeader());
        return cp;
    }

    private BodyWithContentType convertToBody(String bodyString, String type) {
        if(bodyString == null || type == null) return null;
        switch(type.toUpperCase()){
            case "JSON":{
                return new JsonBody(bodyString.trim().replace("\\",""), MediaType.create("application", "json"), MatchType.ONLY_MATCHING_FIELDS);
            }
            case "XML":{
                return new XmlBody(bodyString);
            }
            case "STRING":{
                return new StringBody(bodyString.trim());
            }
            case "PARAMETER":{
                return new ParameterBody(convertToParameters(bodyString.trim()));
            }
            default: {
                throw new RuntimeException(String.format("类型为{%s},没有这种类型的Body",type));
            }
        }
    }

    private List<CommonKeysToValues> convertBodyToKeysToValues(BodyWithContentType body){
        List<CommonKeysToValues> list = new ArrayList<>();
        list.add(getCommonKeysToValues("value",body.getValue().toString()));
        list.add(getCommonKeysToValues("contentType",body.getContentType()));
        return list;
    }

    private List<CommonKeysToValues> convertHeadersToKeysToValues(List<Header> headerList){
        List<CommonKeysToValues> list = new ArrayList<>();
        for(Header header: headerList)
            list.add(getCommonKeysToValues(header.getName().getValue(),header.getValues().toString()));
        return list;
    }

    private List<CommonKeysToValues> convertCookiesToKeysToValues(List<Cookie> cookiesList){
        List<CommonKeysToValues> list = new ArrayList<>();
        for(Cookie cookie: cookiesList)
            list.add(getCommonKeysToValues(cookie.getName().getValue(),cookie.getValue().toString()));
        return list;
    }

    private List<CommonKeysToValues> convertCopToKeysToValues(ConnectionOptions cop){
        if(cop == null) return null;
        List<CommonKeysToValues> list = new ArrayList<>();
        list.add(getCommonKeysToValues("keepAliveOverride",cop.getKeepAliveOverride().toString()));
        list.add(getCommonKeysToValues("suppressContentLengthHeader",cop.getSuppressContentLengthHeader()==null ? null:cop.getSuppressContentLengthHeader().toString()));
        list.add(getCommonKeysToValues("contentLengthHeaderOverride",cop.getContentLengthHeaderOverride().toString()));
        list.add(getCommonKeysToValues("closeSocket",cop.getCloseSocket().toString()));
        list.add(getCommonKeysToValues("suppressConnectionHeader",cop.getSuppressConnectionHeader().toString()));
        return list;
    }

    private CommonKeysToValues getCommonKeysToValues(String key, String value){
        CommonKeysToValues ck = new CommonKeysToValues(key,value);
        return ck;
    }

    private void copyToResponseRespDto(HttpResponse httpResponse,HttpResponseRespDto httpResponseRespDto){
        httpResponseRespDto.setStatusCode(httpResponse.getStatusCode());
        httpResponseRespDto.setDelayTime(httpResponse.getDelay() == null? 0:httpResponse.getDelay().getValue());
        httpResponseRespDto.setReasonPhrase(httpResponse.getReasonPhrase());
        httpResponseRespDto.setBody(convertBodyToKeysToValues(httpResponse.getBody()));
        httpResponseRespDto.setHeaders(convertHeadersToKeysToValues(httpResponse.getHeaders().getEntries()));
        httpResponseRespDto.setCookies(convertCookiesToKeysToValues(httpResponse.getCookieList()));
        httpResponseRespDto.setConnectionOptions(convertCopToKeysToValues(httpResponse.getConnectionOptions()));
    }

    private void convertToErrorInf(HttpStatusCode httpStatusCode, String information,HttpResponseRespDto httpResponseRespDto){
        httpResponseRespDto = new HttpResponseRespDto();
        httpResponseRespDto.setStatusCode(httpStatusCode.code());
        httpResponseRespDto.setReasonPhrase(httpStatusCode.reasonPhrase());
        ArrayList<CommonKeysToValues> error = new ArrayList<>();
        error.add(new CommonKeysToValues("value",information));
        httpResponseRespDto.setBody(error);
    }
}
