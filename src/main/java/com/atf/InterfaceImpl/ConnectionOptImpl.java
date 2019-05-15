package com.atf.InterfaceImpl;

import com.atf.Interface.ConnectionOptionsService;
import com.atf.entity.ConnectionOptionsEntity;
import com.atf.mappers.ConnectionOptionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionOptImpl implements ConnectionOptionsService {
    @Autowired
    ConnectionOptionsDAO connectionOptionsDAO;
    
    public List<ConnectionOptionsEntity> selectAll(){
        return connectionOptionsDAO.selectAll();
    }
    public ConnectionOptionsEntity selectByPrimaryKeyId(Integer id){
        return connectionOptionsDAO.selectByPrimaryKeyId(id);
    }
    public ConnectionOptionsEntity selectByPrimaryKey(ConnectionOptionsEntity t){
        return connectionOptionsDAO.selectByPrimaryKey(t);
    }
    public int deleteByPrimaryKey(ConnectionOptionsEntity t){

        return connectionOptionsDAO.deleteByPrimaryKey(t);
    }
    public int deleteByPrimaryKeyId(Integer id){return connectionOptionsDAO.deleteByPrimaryKeyId(id);}
    public int insertSelective(ConnectionOptionsEntity t){
        return connectionOptionsDAO.insertSelective(t);
    }
    public int insert(ConnectionOptionsEntity t){
        return connectionOptionsDAO.insert(t);
    }
    public int updateByPrimaryKeySelective(ConnectionOptionsEntity t){
        return connectionOptionsDAO.updateByPrimaryKeySelective(t);
    }
    public int updateByPrimaryKey(ConnectionOptionsEntity t){
        return connectionOptionsDAO.updateByPrimaryKey(t);
    }
}
