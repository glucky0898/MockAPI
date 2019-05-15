package com.atf.mappers;


import com.atf.entity.HttpRequestEntity;

public interface HttpRequestDAO extends BaseDAO<HttpRequestEntity> {
    public int deleteByPrimaryKeyId(Integer id);
}
