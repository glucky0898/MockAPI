package com.atf.Interface;


import com.atf.entity.HttpForwardEntity;

import java.util.List;

public interface HttpForwardService {
    public List<HttpForwardEntity> selectAll();
    public HttpForwardEntity selectByPrimaryKeyId(Integer id);
    public int insertSelective(HttpForwardEntity t);
    public int updateByPrimaryKeySelective(HttpForwardEntity t);
    public int deleteByPrimaryKeyId(Integer id);
}
