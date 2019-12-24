package com.xiaoban.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.xiaoban.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from user")
    List<User> getAll();

    @Select("select * from user where id = #{id}")
    User getById(Integer id);

    @Select("select * from user where name = #{name}")
    User getByName(String name);
    
    @Update("update user set name = #{name} ,sid = #{sno} ,"
            + "sex = #{sex},photo = #{photo} ,professional =#{professional},"
            + "`key` = #{key},dept = #{dept} ,roomId=#{roomId} ,seatId =#{seatId}    where id =#{id}")
    int update(User user);

    @Insert("insert into user (`name`,`key`,qq,roomId,seatId,`group`) values (#{name} ,#{key},#{qq},#{roomId} ,#{seatId} ,#{group} )")
    int add(User u);
}
