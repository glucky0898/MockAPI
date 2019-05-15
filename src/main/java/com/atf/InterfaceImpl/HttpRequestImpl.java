package com.atf.InterfaceImpl;

import com.atf.Interface.HttpRequestService;
import com.atf.entity.HttpRequestEntity;
import com.atf.mappers.HttpRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HttpRequestImpl implements HttpRequestService {
    @Autowired
    HttpRequestDAO httpRequestDAO;
    public List<HttpRequestEntity> selectAll(){
        return httpRequestDAO.selectAll();
    }
    public HttpRequestEntity selectByPrimaryKeyId(Integer id){
        return httpRequestDAO.selectByPrimaryKeyId(id);
    }
    public HttpRequestEntity selectByPrimaryKey(HttpRequestEntity t){
        return httpRequestDAO.selectByPrimaryKey(t);
    }
    public int deleteByPrimaryKey(HttpRequestEntity t){
        return httpRequestDAO.deleteByPrimaryKey(t);
    }
    public int deleteByPrimaryKeyId(Integer id){return httpRequestDAO.deleteByPrimaryKeyId(id);}
    public int insertSelective(HttpRequestEntity t){
        return httpRequestDAO.insertSelective(t);
    }
    public int insert(HttpRequestEntity t){
        return httpRequestDAO.insert(t);
    }
    public int updateByPrimaryKeySelective(HttpRequestEntity t){
        return httpRequestDAO.updateByPrimaryKeySelective(t);
    }
    public int updateByPrimaryKey(HttpRequestEntity t){
        return httpRequestDAO.updateByPrimaryKey(t);
    }
}
