package com.atf.mappers;


import com.atf.entity.ExpectationEntity;

import java.util.HashMap;

public interface ExpectationDAO extends BaseDAO<ExpectationEntity> {
    public int deleteByPrimaryKeyId(Integer id);

    public int insertBaseInfo(HashMap map);
}
