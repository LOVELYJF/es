package com.owner.es.service.impl;

import com.owner.es.dao.UserMapper;
import com.owner.es.model.User;
import com.owner.es.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：三凡
 * @desc：描述
 * @date：2019/10/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getById(int id) {
        return userMapper.getById(id);
    }

}
