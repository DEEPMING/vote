package com.example.vote.mapper;

import com.example.vote.POJO.Personnel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Jerry
 * Time: 4. 4
 * Detail:
 */
@Component
public interface SystemManagerMapper {

    /**
     * 获取所有未授权的组织者
     * @return
     */
    @Select("select * from personnel where identity=0 and address='-1'")
    List<Personnel> getAllNoAuthPersonnel();

    /**
     * 获取所有已授权的组织者
     * @return
     */
    @Select("select * from personnel where identity=0 and address='0'")
    List<Personnel> getAllAuthPersonnel();


    @Update("update personnel set address=0 where email=#{email}")
    Integer authOrganizer(String email);
}
