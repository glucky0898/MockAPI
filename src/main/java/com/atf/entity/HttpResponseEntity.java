package com.atf.entity;

import lombok.Data;

@Data
public class HttpResponseEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer statusCode;
    private String reasonPhrase;
    private String body;
    private String type;//需要转化的body的格式
    private String headers;//List<HeaderEntity> headers
    private Integer connectionOptionsId;
    private  ConnectionOptionsEntity connectionOptionsEntity;
    private Long delayTime;
    private String cookies;
}
