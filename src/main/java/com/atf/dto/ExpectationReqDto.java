package com.atf.dto;

import com.atf.entity.HttpForwardEntity;
import com.atf.entity.HttpRequestEntity;
import com.atf.entity.HttpResponseEntity;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ExpectationReqDto {
    private Integer id;
    @NotNull
    private String expectationName;
    @NotNull
    private String creator;
    @NotNull
    private HttpRequestEntity httpRequest;
    private HttpResponseEntity httpResponse;
    private HttpForwardEntity httpForwardEntity;
    private Date createTime;
    private Date modifiedTime;
    @NotNull
    private String type;//action: 1.response 2.forward

    public enum Action{
       RESPONSE("response"),
       FORWARD("forward");
       @Getter
       private String name;
       private Action(String name){
            this.name = name;
       }
    }
}
