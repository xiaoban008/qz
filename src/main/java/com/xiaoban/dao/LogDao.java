package com.xiaoban.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.xiaoban.pojo.Log;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository(value = "ld")
public interface LogDao {
    @Insert("insert into log (uuid,name,created,info)values(#{pojo.uuid} ,#{pojo.name} ,#{pojo.created} ,#{pojo.info} )")
    int insert(@Param("pojo") Log pojo);

    int insertSelective(@Param("pojo") Log pojo);

    int insertList(@Param("pojos") List<Log> pojo);
    @Update("update log set name = #{pojo.name} ,info =#{pojo.info}   where id = #{pojo.id} ")
    int update(@Param("pojo") Log pojo);

    @Select("select * from log where id = #{id} ")
    Log getById(Integer id);

    @Select("select * from log where name = #{name} ")
    Log getByName(String name);

    @Select("select * from log where uuid =#{uuid}")
    List<Log> getByUUID(String uuid);
}
