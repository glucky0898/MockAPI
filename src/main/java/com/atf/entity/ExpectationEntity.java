package com.atf.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExpectationEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String creator;
    private String expectationName;
    private Integer httpRequestId;
    private Integer httpResponseId;
    private Integer httpForwardId;
    private Date createTime;
    private Date modifiedTime;
    private String name;
    private String type;//action: 1.response 2.forward
    /*
    //request匹配 不同的action :response, forward, callback and error
    private Integer httpForwardId;
    private Integer httpErrorId;
    private Integer httpResponseId;

    private HttpTemplateDTO httpResponseTemplate;
    private HttpClassCallbackDTO httpResponseClassCallback;
    private HttpObjectCallbackDTO httpResponseObjectCallback;
    private HttpForwardDTO httpForward;
    private HttpTemplateDTO httpForwardTemplate;
    private HttpClassCallbackDTO httpForwardClassCallback;
    private HttpObjectCallbackDTO httpForwardObjectCallback;
    private HttpOverrideForwardedRequestDTO httpOverrideForwardedRequest;
    private HttpErrorDTO httpError;
    private org.mockserver.serialization.model.TimesDTO times;
    private TimeToLiveDTO timeToLive;*/
}
