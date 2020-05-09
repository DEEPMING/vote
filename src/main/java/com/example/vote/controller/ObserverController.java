package com.example.vote.controller;

import com.alibaba.fastjson.JSON;
import com.example.vote.POJO.*;
import com.example.vote.mapper.ObserverMapper;
import com.example.vote.mapper.OrganizerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Jerry
 * Time: 3. 8
 * Detail: 监督人员控制器
 */
@Controller
public class ObserverController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    Meeting meeting;
    Personnel personnel;

    Map<String, String> resultMap = new HashMap<>();
    String result = "";
    String msg = "";
    String json = "";

    @Autowired
    ObserverMapper observerMapper;

    @Autowired
    OrganizerMapper organizerMapper;

    @RequestMapping("observer/index")
    public String getObserverPage(HttpServletRequest request, Model model){
        logger.info("访问observer/index");

        //会议数据查询加载
        meeting = new Meeting();
        personnel = observerMapper.getMainPersonnel(((Personnel_login)request.getSession().getAttribute("personnel")).getId()+"");
        meeting.setId(Integer.parseInt(((Personnel_login)request.getSession().getAttribute("personnel")).getMeetingId()));
        meeting = observerMapper.getAllMeetingInfo(meeting);
        List<Personnel> personnelList = organizerMapper.getAllPersonnelByMeetingId(meeting.getId()+"");
        List<Info>  infoList1 = organizerMapper.getAllInfoByMeetingId(meeting.getId()+"", "会议流程");
        List<Info>  infoList2 = organizerMapper.getAllInfoByMeetingId(meeting.getId()+"", "公示");
        List<Info>  infoList3 = organizerMapper.getAllInfoByMeetingId(meeting.getId()+"", "投票办法");
        List<Files> filesList1 = organizerMapper.getAllMeetingFiles(meeting.getId()+"", "情况汇报");
        List<Files> filesList2 = organizerMapper.getAllMeetingFiles(meeting.getId()+"", "会议资料");
        List<Selector> selectorList = organizerMapper.getAllMeetingSelector(meeting.getId()+"");
        String[] str;
        if (infoList3.size() > 0){
            str = infoList3.get(0).getContent().split("_");
            model.addAttribute("way1", str[0]);
            model.addAttribute("way2", str[1]);
            model.addAttribute("way3", str[2]);
            model.addAttribute("way4", str[3]);
        }

        model.addAttribute("meeting", meeting);
        model.addAttribute("personnelList", personnelList);
        model.addAttribute("infoList1", infoList1);
        model.addAttribute("infoList2", infoList2);
        model.addAttribute("infoList3", infoList3);
        model.addAttribute("filesList1", filesList1);
        model.addAttribute("filesList2", filesList2);
        model.addAttribute("selectorList", selectorList);
        return "observer/index.html";
    }


    //安全退出功能
    @RequestMapping("observer/safeOut")
    @ResponseBody
    public String safeOutSystem(HttpServletRequest request){
        request.getSession().invalidate();
        result = "1";       //1为安全退出， 0退出失败
        msg = "../index";
        resultMap.put("result", result);
        resultMap.put("msg", msg);
        json = JSON.toJSONString(resultMap);
        return json;
    }


}
