package org.example.springappproject.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.springappproject.entity.User;
import org.example.springappproject.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // user_idによるユーザ情報の取得
    public User selectUserByUserId(String userId){
        return userMapper.selectUserByUserId(userId);
    }

    // ユーザ情報を登録する
    public void insertUser(String userId, String password){
        userMapper.insertUser(userId, password);
    }

    // ユーザ情報を更新する
    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    // ユーザを削除する
    public void deleteUser(String userId){
        userMapper.deleteUser(userId);
    }
}
