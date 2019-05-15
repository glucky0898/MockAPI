package com.atf.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HttpRequestEntity extends BaseEntity {
    //以下全转成对应的
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotNull
    private String method;//转成NottableString string()方法
    @NotNull
    private String path;//转成NottableString
    private String queryParameters;//转成Parameter 参数为数组
    private String body;//需要转成对应格式的body
    @NotNull
    private String type;//需要转化的body的格式
    private String headers;//转成List
    private Boolean keepAlive;
    private Boolean secure;
    private String cookies;//转成Cookie 参数为数组  withCookies(List<Cookie> cookies)
}
