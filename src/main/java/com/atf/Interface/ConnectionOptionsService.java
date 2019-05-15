package com.atf.Interface;


import com.atf.entity.ConnectionOptionsEntity;

public interface ConnectionOptionsService {
    public ConnectionOptionsEntity selectByPrimaryKeyId(Integer id);
    public ConnectionOptionsEntity selectByPrimaryKey(ConnectionOptionsEntity t);
    public int deleteByPrimaryKey(ConnectionOptionsEntity t);
    public int insertSelective(ConnectionOptionsEntity t);
    public int insert(ConnectionOptionsEntity t);
    public int updateByPrimaryKeySelective(ConnectionOptionsEntity t);
    public int updateByPrimaryKey(ConnectionOptionsEntity t);
    public int deleteByPrimaryKeyId(Integer id);
}
