package com.atf.Interface;


import com.atf.entity.HttpResponseEntity;

import java.util.List;

public interface HttpResponseService {
    public List<HttpResponseEntity> selectAll();
    public HttpResponseEntity selectByPrimaryKeyId(Integer id);
    public HttpResponseEntity selectByPrimaryKey(HttpResponseEntity t);
    public int deleteByPrimaryKey(HttpResponseEntity t);
    public int insertSelective(HttpResponseEntity t);
    public int insert(HttpResponseEntity t);
    public int updateByPrimaryKeySelective(HttpResponseEntity t);
    public int updateByPrimaryKey(HttpResponseEntity t);
    public int deleteByPrimaryKeyId(Integer id);
}
