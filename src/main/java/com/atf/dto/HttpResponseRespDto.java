package com.atf.dto;

import lombok.Data;

import java.util.List;

@Data
public class HttpResponseRespDto {
    private Integer statusCode;
    private String reasonPhrase;
    private List<CommonKeysToValues> body;
    private List<CommonKeysToValues> headers;
    private List<CommonKeysToValues> connectionOptions;
    private Long delayTime;
    private List<CommonKeysToValues> cookies;
}
