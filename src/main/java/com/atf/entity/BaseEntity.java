package com.atf.entity;

import lombok.Data;

@Data
public class BaseEntity {

    public static final Integer  INT_SPECIAL_NULL = -1;

    public static final Integer  INT_SPECIAL_NOT_NULL = -2;

    public static final String  STRING_SPECIAL_NULL = "`null`";

    public static final String  STRING_SPECIAL_NOT_NULL = "`notnull`";
}
