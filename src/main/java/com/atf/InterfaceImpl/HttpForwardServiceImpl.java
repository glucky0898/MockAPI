package com.atf.InterfaceImpl;

import com.atf.Interface.HttpForwardService;
import com.atf.entity.HttpForwardEntity;
import com.atf.mappers.HttpForwardDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HttpForwardServiceImpl implements HttpForwardService {
    @Autowired
    HttpForwardDAO httpForwardDAO;
    public List<HttpForwardEntity> selectAll(){
        return httpForwardDAO.selectAll();
    }
    public HttpForwardEntity selectByPrimaryKeyId(Integer id){
        return httpForwardDAO.selectByPrimaryKeyId(id);
    }
    public int insertSelective(HttpForwardEntity t){
        return httpForwardDAO.insertSelective(t);
    }
    public int updateByPrimaryKeySelective(HttpForwardEntity t){
        return httpForwardDAO.updateByPrimaryKeySelective(t);
    }
    public int deleteByPrimaryKeyId(Integer id){
        return httpForwardDAO.deleteByPrimaryKeyId(id);
    }
}
