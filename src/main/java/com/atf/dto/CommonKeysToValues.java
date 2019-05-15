package com.atf.dto;

import lombok.Data;

@Data
public class CommonKeysToValues {
    String key;
    String value;
    public CommonKeysToValues(String key,String value){
        this.key = key;
        this.value = value;
    }
}
