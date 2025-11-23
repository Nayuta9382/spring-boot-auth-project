package org.example.springappproject.mapper;

import org.apache.ibatis.annotations.*;
import org.example.springappproject.entity.User;

@Mapper
public interface UserMapper {

    // user_idによるユーザ情報の取得
    @Select("SELECT user_id, password, nickname, comment,created_at FROM users WHERE user_id = #{userId}")
    User selectUserByUserId(String userId);

    // ユーザ情報を登録する
    @Insert("INSERT INTO users (user_id, password) VALUES (#{userId}, #{password})")
    void insertUser(String userId, String password);

    // ユーザ情報を更新する
    @Update("UPDATE users SET nickname = #{nickname}, comment = #{comment} WHERE user_id = #{userId}")
    void updateUser(User user);

    // ユーザを削除する
    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    void deleteUser(String userId);
}
