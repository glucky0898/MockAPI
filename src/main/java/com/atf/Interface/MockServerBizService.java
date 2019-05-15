package com.atf.Interface;


import com.atf.dto.BaseRespDto;
import com.atf.dto.ExpectationReqDto;
import com.atf.dto.HttpResponseRespDto;
import com.atf.entity.HttpRequestEntity;

import java.util.HashMap;
import java.util.List;

public interface MockServerBizService {
    public ExpectationReqDto addExpectationClient(ExpectationReqDto e)throws Exception;
    public List<ExpectationReqDto> getAllExpectation() throws Exception;
    public BaseRespDto delExpectationClient(Integer id) throws Exception;
    public ExpectationReqDto updateExpectationClient(ExpectationReqDto e) throws Exception;
    public HttpResponseRespDto runAction(HttpRequestEntity httpRequestEntity) throws Exception;
    public ExpectationReqDto getExpectationById(Integer id) throws Exception;
    public void addBaseInfo(HashMap map) throws Exception;
}
