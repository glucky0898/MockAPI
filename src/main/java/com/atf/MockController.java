package com.atf;

import com.atf.dto.BaseRespDto;
import com.atf.dto.ExpectationReqDto;
import com.atf.dto.HttpResponseRespDto;
import com.atf.entity.HttpRequestEntity;
import com.atf.mappers.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.atf.Interface.MockServerBizService;

import java.util.*;

@RestController
@RequestMapping("/mockServer")
public class MockController {
    @Autowired
    MockServerBizService mockServerBizService;
    @Autowired
    UserDao userDao;

    @RequestMapping("/addBaseInfo")
    @ResponseBody
    public BaseRespDto addBaseInfo(@RequestBody HashMap map)throws Exception{
        mockServerBizService.addBaseInfo(map);
        return  BaseRespDto.success("保存成功");
    }
    @RequestMapping("/addExpectation")
    public ExpectationReqDto addExpectation(@RequestBody ExpectationReqDto expectationReqDto) throws Exception{
        return mockServerBizService.addExpectationClient(expectationReqDto);
    }
    @RequestMapping("/getAllExpectation")
    public List<ExpectationReqDto> getAllExpectation() throws Exception{
        return  mockServerBizService.getAllExpectation();
    }

    @RequestMapping("/getExpectationById")
    public ExpectationReqDto getExpectationById(@RequestParam Integer id) throws Exception{
        return  mockServerBizService.getExpectationById(id);
    }

    @RequestMapping("/deleteExpectation")
    public BaseRespDto deleteExpectation(@RequestParam Integer id) throws Exception{
        return mockServerBizService.delExpectationClient(id);
    }
    @RequestMapping("/updateExpectation")
    public ExpectationReqDto updateExpectation(@RequestBody ExpectationReqDto expectationReqDto) throws Exception{
        return mockServerBizService.updateExpectationClient(expectationReqDto);
    }
    @RequestMapping("/runExpectation")
    public HttpResponseRespDto runExpectation(@RequestBody HttpRequestEntity httpRequestEntity)throws Exception{
        return mockServerBizService.runAction(httpRequestEntity);
    }
}
