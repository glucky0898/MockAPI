package com.atf.dto;

import com.atf.entity.HttpRequestEntity;
import com.atf.entity.HttpResponseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ExpectationRespDto extends BaseRespDto {
    private Integer id;
    private String expectationName;
    private String creator;
    private HttpRequestEntity httpRequest;
    private HttpResponseEntity httpResponse;
    private Date createTime;
    private Date modifiedTime;
}
