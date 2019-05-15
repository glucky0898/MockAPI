package com.atf.mappers;


import com.atf.entity.ConnectionOptionsEntity;

public interface ConnectionOptionsDAO extends BaseDAO<ConnectionOptionsEntity> {
    public int deleteByPrimaryKeyId(Integer id);
}
