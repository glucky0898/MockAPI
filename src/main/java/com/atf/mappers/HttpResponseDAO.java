package com.atf.mappers;


import com.atf.entity.HttpResponseEntity;

public interface HttpResponseDAO extends BaseDAO<HttpResponseEntity> {
    public int deleteByPrimaryKeyId(Integer id);
}
