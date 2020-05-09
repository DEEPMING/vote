package com.example.vote.mapper;

import com.example.vote.POJO.Personnel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 用户注册 持久层
 */
@Component
public interface RegisteMapper {
    //注册新组织者用户
    @Insert("INSERT INTO personnel VALUE(default,#{email},#{name},#{password},#{identity},#{up},#{code},#{address},#{identityCode},#{beizhu})")
    Integer addPersonnel(Personnel personnel);

    //验证组织者是否已存在
    @Select("SELECT * FROM personnel WHERE email=#{email}")
    Personnel getPersonnel(Personnel personnel);
}
