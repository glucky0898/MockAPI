package com.atf.mappers;


import com.atf.entity.HttpForwardEntity;

public interface HttpForwardDAO extends BaseDAO<HttpForwardEntity> {
    public int deleteByPrimaryKeyId(Integer id);
}
