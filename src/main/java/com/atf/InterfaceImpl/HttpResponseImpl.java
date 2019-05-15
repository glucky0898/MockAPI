package com.atf.InterfaceImpl;

import com.atf.Interface.HttpResponseService;
import com.atf.entity.HttpResponseEntity;
import com.atf.mappers.HttpResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HttpResponseImpl implements HttpResponseService {
    @Autowired
    HttpResponseDAO httpResponseDAO;
    public List<HttpResponseEntity> selectAll(){
        return httpResponseDAO.selectAll();
    }
    public HttpResponseEntity selectByPrimaryKeyId(Integer id){
        return httpResponseDAO.selectByPrimaryKeyId(id);
    }
    public HttpResponseEntity selectByPrimaryKey(HttpResponseEntity t){
        return httpResponseDAO.selectByPrimaryKey(t);
    }
    public int deleteByPrimaryKey(HttpResponseEntity t){
        return httpResponseDAO.deleteByPrimaryKey(t);
    }
    public int deleteByPrimaryKeyId(Integer id){return httpResponseDAO.deleteByPrimaryKeyId(id);}
    public int insertSelective(HttpResponseEntity t){
        return httpResponseDAO.insertSelective(t);
    }
    public int insert(HttpResponseEntity t){
        return httpResponseDAO.insert(t);
    }
    public int updateByPrimaryKeySelective(HttpResponseEntity t){
        return httpResponseDAO.updateByPrimaryKeySelective(t);
    }
    public int updateByPrimaryKey(HttpResponseEntity t){
        return httpResponseDAO.updateByPrimaryKey(t);
    }
}
