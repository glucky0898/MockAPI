package com.atf.Interface;


import com.atf.entity.ExpectationEntity;

import java.util.HashMap;
import java.util.List;

public interface ExpectationService {
    public List<ExpectationEntity> selectAll();
    public ExpectationEntity selectByPrimaryKeyId(Integer id);
    public ExpectationEntity selectByPrimaryKey(ExpectationEntity t);
    public int deleteByPrimaryKey(ExpectationEntity t);
    public int insertSelective(ExpectationEntity t);
    public int insert(ExpectationEntity t);
    public int updateByPrimaryKeySelective(ExpectationEntity t);
    public int updateByPrimaryKey(ExpectationEntity t);
    public int deleteByPrimaryKeyId(Integer id);
    public int insertBaseInfo(HashMap map);

}
