package com.fanke.administration.service;

import com.fanke.administration.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    User login(@Param("userName") String userName, @Param("userPwd") String userPwd);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int updateUserPwd(User user);

    /**
     * 找回密码
     * @param userName
     * @return
     */
    User selectUserPwd(@Param("userName") String userName);

    /**
     * 查看用户名是否重复
     * @param userName
     * @return
     */
    int selectUserCount(@Param("userName") String userName);

    /**
     * 根据用户id获取
     * @param userId
     * @return
     */
    User selectUserById(@Param("userId") Integer userId);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param userName
     * @return
     */
    List<User> selectUserByFenye(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("userName") String userName);

    /**
     * 查询总数量
     * @return
     */
    int selectUsersCount();

    int updataPwd(@Param("userId") Integer userId,@Param("userPwd") String userPwd);
}
