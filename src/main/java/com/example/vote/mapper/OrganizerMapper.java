package com.example.vote.mapper;

import com.example.vote.POJO.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail:组织者 持久层
 */
@Component
public interface OrganizerMapper {
    /**
     * 修改设置组织者的信息
     * @param personnel
     * @return
     */
    @Update("update personnel set email=#{email},password=#{password},name=#{name} where id=#{id}")
    Integer updatePersonnel(Personnel personnel);


    /**
     * 添加会议基本信息
     * @param meeting
     * @return
     */
    @Insert("insert into meeting value(default,#{code},#{name},#{time},#{address},#{organizer},#{mainId})")
    Integer addNewMeeting(Meeting meeting);

    /**
     * 验证会议编号是否已存在
     * @param meeting
     * @return
     */
    @Select("select * from meeting where code=#{code} and mainId=#{mainId}")
    Meeting verifyMeetingCode(Meeting meeting);

    /**
     * 根据人员编号和所属上级信息验证是否已存在
     * @param personnel
     * @return
     */
    @Select("select * from personnel where code=#{code} and up=#{up}")
    Personnel verifyParticipatorByCodeAdUp(Personnel personnel);


    /**
     * 添加新附属人员
     * @param personnel
     * @return
     */
    @Insert("INSERT INTO personnel VALUE(default,#{email},#{name},#{password},#{identity},#{up},#{code},#{address},#{identityCode},#{beizhu})")
    Integer addNewParticipator(Personnel personnel);

    /**
     * 添加会议人员登录信息
     * @param personnel_login
     * @return
     */
    @Insert("insert into personnel_login value(default,#{mainId},#{userName},#{password},#{identity},#{meetingId},#{finished})")
    Integer addNewParticipatorLogin(Personnel_login personnel_login);


    /**
     * 会议相关信息 会议流程、公示、投票办法
     * @param info
     * @return
     */
    @Insert("insert into info value(default,#{meetingId},#{content},#{classs})")
    Integer addMeetingInfo(Info info);


    /**
     * 会议情况汇报文件入库
     * @param files
     * @return
     */
    @Insert("insert into file value(default,#{meetingId},#{fileName},#{filePath},#{fileClass})")
    Integer addMeetingSituationFiles(Files files);

    /**
     * 添加表决票
     * @param selector
     * @return
     */
    @Insert("insert into selector value(default, #{meetingId}, #{name}, #{content},0)")
    Integer addMeetingSelector(Selector selector);

    /**
     * 获取进行中的会议列表
     * @param meeting
     * @return
     */
    @Select("select * from meeting where mainId=#{mainId} and time>=#{time}")
    List<Meeting> getListMeetingDoing(Meeting meeting);

    /**
     * 获取已完成的会议列表
     * @param meeting
     * @return
     */
    @Select("select * from meeting where mainId=#{mainId} and time<#{time}")
    List<Meeting> getListMeetingFinished(Meeting meeting);

    /**
     * 获取某一会议具体信息
     * @param meeting
     * @return
     */
    @Select("select * from meeting where mainId=#{mainId} and code=#{code}")
    Meeting getAllMeetingInfo(Meeting meeting);


    /**
     * 获取参会人员列表
     * @return
     */
    @Select("select p.id,p.email,p.name,l.identity,p.code,p.address,p.identityCode,p.beizhu from personnel p left join personnel_login l on p.id=l.mainId where meetingId=${meetingId}")
    List<Personnel> getAllPersonnelByMeetingId(@Param(value = "meetingId") String meetingId);

    /**
     * 根据会议id和类型，获取对应信息
     * @param meetingId
     * @param classs
     * @return
     */
    @Select("select * from info where meetingId=#{meetingId} and class=#{classs}")
    List<Info> getAllInfoByMeetingId(@Param(value = "meetingId") String meetingId, @Param(value = "classs") String classs);

    /**
     * 获取会议文件列表
     * @param meetingId
     * @param fileClass
     * @return
     */
    @Select("select * from file where meetingId= #{meetingId} and fileClass=#{fileClass}")
    List<Files> getAllMeetingFiles(@Param(value = "meetingId") String meetingId, @Param(value = "fileClass") String fileClass);


    /**
     * 获取会议表决票
     * @param meetingId
     * @return
     */
    @Select("select * from selector where meetingId=#{meetingId}")
    List<Selector> getAllMeetingSelector(@Param(value = "meetingId") String meetingId);


    /**
     * 固定投票办法
     * @param info
     * @return
     */
    @Update("update info set content=#{content} where meetingId=#{meetingId} and class='投票办法'")
    Integer updateMeetingVoteWay(Info info);


    /**
     * 根据会议id获取倒序结果
     * @param meetingId
     * @return
     */
    @Select("select * from selector where meetingId=#{meetingId} order by sum desc")
    List<Selector> getResultByMeetingId(String meetingId);
}
