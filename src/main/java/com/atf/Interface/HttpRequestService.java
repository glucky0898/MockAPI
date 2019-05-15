package com.atf.Interface;


import com.atf.entity.HttpRequestEntity;

import java.util.List;
public interface HttpRequestService {
    public List<HttpRequestEntity> selectAll();
    public HttpRequestEntity selectByPrimaryKeyId(Integer id);
    public HttpRequestEntity selectByPrimaryKey(HttpRequestEntity t);
    public int deleteByPrimaryKey(HttpRequestEntity t);
    public int insertSelective(HttpRequestEntity t);
    public int insert(HttpRequestEntity t);
    public int updateByPrimaryKeySelective(HttpRequestEntity t);
    public int updateByPrimaryKey(HttpRequestEntity t);
    public int deleteByPrimaryKeyId(Integer id);

}
