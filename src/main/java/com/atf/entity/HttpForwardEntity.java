package com.atf.entity;

import lombok.Data;

@Data
public class HttpForwardEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String host;
    private Integer port;
    private String scheme;//http https
}
