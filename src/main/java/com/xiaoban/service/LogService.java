package com.xiaoban.service;

import com.xiaoban.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.xiaoban.dao.LogDao;

@Service
public class LogService{

    @Autowired
    private LogDao logDao;

    public int insert(Log pojo){
        return logDao.insert(pojo);
    }

    public int insertSelective(Log pojo){
        return logDao.insertSelective(pojo);
    }

    public int insertList(List<Log> pojos){
        return logDao.insertList(pojos);
    }

    public int update(Log pojo){
        return logDao.update(pojo);
    }

    public Log getByName(String name) {
        return logDao.getByName(name);
    }

    public Log getById(Integer id) {
        return logDao.getById(id);
    }

    public List<Log> getByUUID(String uuid) {
        return logDao.getByUUID(uuid);
    }
}
