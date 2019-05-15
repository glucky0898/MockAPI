package com.atf.entity;

import lombok.Data;

@Data
public class ConnectionOptionsEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Boolean suppressContentLengthHeader;
    private Integer contentLengthHeaderOverride;
    private Boolean suppressConnectionHeader;
    private Boolean keepAliveOverride;
    private Boolean closeSocket;
}
