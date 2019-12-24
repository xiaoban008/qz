package com.xiaoban.service;

import com.xiaoban.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.xiaoban.dao.UserDao;

@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    public int update(User user) {
        return userDao.update(user);
    }

    public User getByName(String name) {
        return userDao.getByName(name);
    }

    public User getById(Integer id) {
        return userDao.getById(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public int addUser(User u) {
        return userDao.add(u);
    }
}
