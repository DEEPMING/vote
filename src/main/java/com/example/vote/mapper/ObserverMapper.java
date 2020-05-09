package com.example.vote.mapper;

import com.example.vote.POJO.Meeting;
import com.example.vote.POJO.Personnel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.number.PercentStyleFormatter;
import org.springframework.stereotype.Component;

/**
 * Author: Jerry
 * Time: 3. 22
 * Detail: 监督人员、列席人员 持久层
 */
@Component
public interface ObserverMapper {

    /**
     * 获取组织者id
     * @param id
     * @return
     */
    @Select("select p.* from personnel p left join personnel_login l on p.id=l.mainId where l.id=#{id}")
    Personnel getMainPersonnel(@Param(value = "id") String id);

    /**
     * 根据会议id获取会议信息
     * @param meeting
     * @return
     */
    @Select("select * from meeting where id=#{id}")
    Meeting getAllMeetingInfo(Meeting meeting);
}
