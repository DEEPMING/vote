package com.example.vote.mapper;

import com.example.vote.POJO.Personnel;
import com.example.vote.POJO.Personnel_login;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 用户登陆 持久层
 */
@Component
public interface LoginMapper {

    //验证组织者登陆信息
    @Select("SELECT * FROM personnel WHERE email=#{email} and password=#{password} and address='0'")
    Personnel verifyOrganizer(Personnel personnel);

    //验证组织者登陆信息
    @Select("SELECT * FROM personnel WHERE email=#{email} and password=#{password} and address='-2'")
    Personnel verifySystem(Personnel personnel);

    //验证除组织者外的其他用户登陆信息
    @Select("SELECT * FROM personnel_login WHERE userName=#{userName} and password=#{password} and identity=#{identity}")
    Personnel_login verifyOthers(Personnel_login personnelLogin);

}
