package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.UserMapper;
import com.fanke.administration.pojo.User;
import com.fanke.administration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Override
    public User login(String userName, String userPwd) {
        return this.userMapper.login(userName,userPwd);
    }

    @Override
    public int insertUser(User user) {
        return this.userMapper.insertUser(user);
    }

    @Override
    public int updateUserPwd(User user) {
        return this.userMapper.updateUserPwd(user);
    }

    @Override
    public User selectUserPwd(String userName) {
        return this.userMapper.selectUserPwd(userName);
    }

    @Override
    public int selectUserCount(String userName) {
        return this.userMapper.selectUserCount(userName);
    }

    @Override
    public User selectUserById(Integer userId) {
        return this.userMapper.selectUserById(userId);
    }

    @Override
    public List<User> selectUserByFenye(Integer pageNo, Integer pageSize, String userName) {
        return this.userMapper.selectUserByFenye(pageNo,pageSize,userName);
    }

    @Override
    public int selectUsersCount() {
        return this.userMapper.selectUsersCount();
    }

    @Override
    public int updataPwd(Integer userId, String userPwd) {
        return this.userMapper.updataPwd(userId,userPwd);
    }
}
