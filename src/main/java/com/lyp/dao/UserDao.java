package com.lyp.dao;

import com.lyp.model.Pages;
import com.lyp.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Select("select * from user where userNumber=#{0} and userPassword=#{1}")
    User getUserBySubmit(String userNumber, String userPassword);

    @Select("select * from user where userNumber=#{userNumber}")
    User getUserByNum(String userNumber);

    @Insert("insert into user(userNumber,userName,userPassword) values(#{userNumber},#{userName},#{userPassword})")
    int register(User user);

    @Update("update user set userType=#{userType},userAge=#{userAge},userBirthday=#{userBirthday},userProvince=#{userProvince},userCity=#{userCity},userArea=#{userArea},userPhone=#{userPhone},userPassword=#{userPassword} where userNumber=#{userNumber}")
    void updateByNum(User user);

    @Update("update user set userBirthday=#{userBirthday},userType=#{userType},userName=#{userName},userPhone=#{userPhone} where userNumber=#{userNumber}")
    void updateuserByNum(User user);

    @Select("select count(*) from user")
    int getUserCount();

    @Select("select * from user limit #{startNow},#{pageSize}")
    List<User> selectUserByPage(@Param(value = "startPage") Integer startPage, @Param(value = "pageSize") Integer pageSize) throws Exception;

    @Select("select * from user order by userNumber DESC limit #{start},#{rows}")
    List<User> selectPageList(Pages page);

    @Select("select count(*) from user")
    Integer selectPageCount(Pages page);

    @Delete("delete from user where userNumber=#{userNumber}")
    void deleteByuserNumber(String userNumber);

    @Update("update user set userImg=#{0} where userNumber=#{1}")
    void addHeaderImage(String descPath, String userNumber);

}

