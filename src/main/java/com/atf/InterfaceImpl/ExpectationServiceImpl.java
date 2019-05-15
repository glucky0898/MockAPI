package com.atf.InterfaceImpl;

import com.atf.Interface.ExpectationService;
import com.atf.entity.ExpectationEntity;
import com.atf.mappers.ExpectationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ExpectationServiceImpl implements ExpectationService {
    @Autowired
    ExpectationDAO expectationDAO;
    public List<ExpectationEntity> selectAll(){
        return expectationDAO.selectAll();
    }
    public ExpectationEntity selectByPrimaryKeyId(Integer id){
        return expectationDAO.selectByPrimaryKeyId(id);
    }
    public ExpectationEntity selectByPrimaryKey(ExpectationEntity t){
        return expectationDAO.selectByPrimaryKey(t);
    }
    public int deleteByPrimaryKey(ExpectationEntity t){
        return expectationDAO.deleteByPrimaryKey(t);
    }
    public int deleteByPrimaryKeyId(Integer id){return expectationDAO.deleteByPrimaryKeyId(id);}
    public int insertSelective(ExpectationEntity t){
        return expectationDAO.insertSelective(t);
    }
    public int insert(ExpectationEntity t){
        return expectationDAO.insert(t);
    }
    public int updateByPrimaryKeySelective(ExpectationEntity t){
        return expectationDAO.updateByPrimaryKeySelective(t);
    }
    public int updateByPrimaryKey(ExpectationEntity t){
        return expectationDAO.updateByPrimaryKey(t);
    }

    public int insertBaseInfo(HashMap map){
        return expectationDAO.insertBaseInfo(map);
    }
}
