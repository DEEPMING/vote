package com.example.vote.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.example.vote.POJO.*;
import com.example.vote.mapper.OrganizerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Jerry
 * Time: 3. 8
 * Detail:
 */
@Controller
public class OrganizerController {
    @Autowired
    OrganizerMapper organizerMapper;

    Meeting meeting;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    Personnel personnel;
    Map<String, String> resultMap = new HashMap<>();
    String result = "";
    String msg = "";
    String json = "";

    //获取组织人员页面
    @RequestMapping("organizer/index")
    public String getOrganizerPage(){
        logger.info("访问organizer/index");
        return "organizer/index.html";
    }

    //获取创建会议页面
    @RequestMapping("organizer/create")
    public String getRCreatePage(){
        logger.info("访问organizer/module/create");
        return "organizer/module/create.html";
    }

    //获取进行中会议页面
    @RequestMapping("organizer/doing")
    public String getDoingPage(HttpServletRequest request, Model model){
        logger.info("访问organizer/module/doing");

        //查询进行中的会议列表
        meeting = new Meeting();
        meeting.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        meeting.setMainId(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
        List<Meeting> meetingList = organizerMapper.getListMeetingDoing(meeting);
        model.addAttribute("meetingList",meetingList);

        return "organizer/module/doing.html";
    }

    //获取已完成会议页面
    @RequestMapping("organizer/finished")
    public String getfinishedPage(HttpServletRequest request, Model model){
        logger.info("访问organizer/module/finished");

        //查询进行中的会议列表
        meeting = new Meeting();
        meeting.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        meeting.setMainId(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
        List<Meeting> meetingList = organizerMapper.getListMeetingFinished(meeting);
        model.addAttribute("meetingList",meetingList);

        return "organizer/module/finished.html";
    }

    //获取设置页面
    @RequestMapping("organizer/setting")
    public String getsettingPage(){
        logger.info("访问organizer/module/setting");
        return "organizer/module/setting.html";
    }

    //获取具体会议内容页面
    @RequestMapping("organizer/view")
    public String getMeetingViewPage(HttpServletRequest request, Model model){
        logger.info("访问organizer/module/view");
        //会议数据查询加载
        meeting = new Meeting();
        meeting.setCode(request.getParameter("code"));
        meeting.setMainId(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
        meeting = organizerMapper.getAllMeetingInfo(meeting);
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
        return "organizer/module/view.html";
    }

    //安全退出功能
    @RequestMapping("organizer/safeOut")
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

    /**
     *
     * @param request
     * @param forword       文件所属类型 情况汇报、会议资料
     * @return
     * @throws IOException
     */
    //文件入库操作
    public String updateFiles(HttpServletRequest request, String forword) throws IOException {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        for (MultipartFile file : files) {
            String path = "C:/Users/Jerry/Desktop/"+System.currentTimeMillis()+"/";
            new File(path).mkdirs();
            Files files1 = new Files();
            File fileP = new File(path+file.getOriginalFilename());
            fileP.createNewFile();
            OutputStream fos = new FileOutputStream(fileP);
            InputStream fis = file.getInputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while ((len = fis.read(buffer))>0){
                fos.write(buffer,0,len);
            }
            fis.close();
            fos.flush();
            fos.close();

            files1.setMeetingId(((Meeting)request.getSession().getAttribute("meeting")).getId()+"");
            files1.setFileName(file.getOriginalFilename());
            files1.setFilePath(path);
            files1.setFileClass(forword);
            organizerMapper.addMeetingSituationFiles(files1);
        }
        return "success";
    }

    /**
     * 会议创建提交信息
     * @param request
     * @return
     */
    @RequestMapping("organizer/upMeetingInfo")
    @ResponseBody
    public String createNewMeeting(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        if (flag == null || "".equals(flag)){
            flag = (String) request.getSession().getAttribute("flag");
            request.getSession().removeAttribute("flag");
        }
        Integer res = -1;
        switch (flag) {
            case "0": //会议注册
            {
                meeting = new Meeting();
                meeting.setCode(request.getParameter("code"));
                meeting.setName(request.getParameter("name"));
                meeting.setTime(request.getParameter("time"));
                meeting.setAddress(request.getParameter("address"));
                meeting.setOrganizer(request.getParameter("organizer"));
                meeting.setMainId(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
                if (organizerMapper.verifyMeetingCode(meeting) == null){
                    res = organizerMapper.addNewMeeting(meeting);
                    meeting = organizerMapper.verifyMeetingCode(meeting);
                    request.getSession().setAttribute("meeting", meeting);
                }
                break;
            }
            case "1": //参会人员 验证
            {
                personnel = new Personnel();
                personnel.setCode(request.getParameter("code"));
                personnel.setUp(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
                personnel = organizerMapper.verifyParticipatorByCodeAdUp(personnel);
                if (personnel != null){
                    res = 1;
                    resultMap.put("code",personnel.getCode());
                    resultMap.put("name",personnel.getName());
                    resultMap.put("address",personnel.getAddress());
                    resultMap.put("email",personnel.getEmail());
                }else{
                    res = -1;
                }
                break;
            }
            case "2": //参会人员 添加
            {
                personnel = new Personnel();
                personnel.setCode(request.getParameter("code"));
                personnel.setName(request.getParameter("name"));
                personnel.setAddress(request.getParameter("address"));
                personnel.setEmail(request.getParameter("email"));
                personnel.setIdentityCode(request.getParameter("identityCode"));
                personnel.setBeizhu(request.getParameter("beizhu"));
                personnel.setUp(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
                res = organizerMapper.addNewParticipator(personnel);
                break;
            }
            case "3": //参会人员 确认
            {
                Personnel_login personnelLogin = new Personnel_login();
                personnel.setCode(request.getParameter("code"));
                personnel.setUp(((Personnel)request.getSession().getAttribute("personnel")).getId()+"");
                System.out.println(personnel);
                personnel = organizerMapper.verifyParticipatorByCodeAdUp(personnel);
                personnelLogin.setMainId(personnel.getId()+"");
                personnelLogin.setIdentity(request.getParameter("joinIdentity"));
                personnelLogin.setMeetingId(((Meeting)request.getSession().getAttribute("meeting")).getId()+"");
                personnelLogin.setUserName(Math.random()*1000000+""+System.currentTimeMillis());
                personnelLogin.setPassword(Math.random()*1000000+""+System.currentTimeMillis());
                organizerMapper.addNewParticipatorLogin(personnelLogin);

                if("1".equals(personnelLogin.getIdentity()))
                    personnelLogin.setIdentity("参与人员");
                if("2".equals(personnelLogin.getIdentity()))
                    personnelLogin.setIdentity("监督人员");
                if("3".equals(personnelLogin.getIdentity()))
                    personnelLogin.setIdentity("列席人员");
                MailUtil.send(personnel.getEmail(),
                        "会议登陆标识",
                        "<h3>登陆名："+personnelLogin.getUserName()+"<br>密码："+personnelLogin.getPassword()+"<br>身份："+personnelLogin.getIdentity(),
                        true);
                res = 1;
                break;
            }
            case "4": //会议流程
            {
                Info info = new Info();
                info.setMeetingId(((Meeting)request.getSession().getAttribute("meeting")).getId()+"");
                info.setContent(request.getParameter("content"));
                info.setClasss("会议流程");
                res = organizerMapper.addMeetingInfo(info);
                break;
            }
            case "5": //情况汇报
            {
                //文件操作为主
                if(request.getParameter("flag") != null){
                    request.getSession().setAttribute("flag","5");
                    break;
                }
                try {
                    updateFiles(request, "情况汇报");
                    result = "1";
                    msg = "情况汇报文件上传成功";
                } catch (IOException e) {
                    result = "-1";
                    msg = "情况汇报文件上传失败";
                    e.printStackTrace();
                }
                break;
            }
            case "6": //投票办法 暂存
            {
                Info info = new Info();
                info.setMeetingId(((Meeting)request.getSession().getAttribute("meeting")).getId()+"");
                info.setContent(request.getParameter("content"));
                info.setClasss("投票办法");
                System.out.println(info.getContent());
                res = organizerMapper.addMeetingInfo(info);
                break;
            }
            case "7": //表决票
            {
                Selector selector = new Selector();
                selector.setName(request.getParameter("name"));
                selector.setContent(request.getParameter("info"));
                selector.setMeetingId(((Meeting)request.getSession().getAttribute("meeting")).getId()+"");

                result= organizerMapper.addMeetingSelector(selector) +"";
                msg = "添加表决票成功";

                break;
            }
            case "8": //会议资料
            {
                //文件操作为主
                if(request.getParameter("flag") != null){
                    request.getSession().setAttribute("flag","8");
                    break;
                }
                try {
                    updateFiles(request, "会议资料");
                    result = "1";
                    msg = "会议资料文件上传成功";
                } catch (IOException e) {
                    result = "-1";
                    msg = "会议资料文件上传失败";
                    e.printStackTrace();
                }
                break;
            }
            case "9": //投票办法 暂存
            {
                Info info = new Info();
                info.setMeetingId(request.getParameter("meetingId"));
                info.setContent(request.getParameter("content"));
                info.setClasss("投票办法");
                res = organizerMapper.updateMeetingVoteWay(info);
                break;
            }
        }

        resultMap.put("result", res.toString());
        resultMap.put("msg", msg);
        json = JSON.toJSONString(resultMap);
        return json;
    }


    /**
     * 组织者信息修改设置
     * @param request
     * @return
     */
    @RequestMapping("organizer/settingInfo")
    @ResponseBody
    public String setNewInfo(HttpServletRequest request){
        personnel = (Personnel) request.getSession().getAttribute("personnel");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String organName = request.getParameter("organName");
        if(personnel.getPassword().equals(oldPassword)){
            if(!"".equals(email)){
                personnel.setEmail(email);
            }
            if(!"".equals(newPassword)){
                personnel.setPassword(newPassword);
            }
            if (!"".equals(organName)){
                personnel.setName(organName);
            }
            if(organizerMapper.updatePersonnel(personnel) >0){
                result = "1";
                msg = "设置成功";
            }else {
                result = "0";
                msg = "设置失败";
            }
        }else {
            result = "-1";
            msg = "旧密码错误";
        }

        resultMap.put("result", result);
        resultMap.put("msg", msg);
        json = JSON.toJSONString(resultMap);
        return json;
    }


    //文件查看
    @RequestMapping("organizer/getFile")
    @ResponseBody
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter("path");
        String name = request.getParameter("name");
        File file = new File(path+"\\"+name);

        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename="+new String( name.getBytes("utf-8"), "ISO8859-1" ));
        OutputStream out = response.getOutputStream();
        InputStream is = new FileInputStream(file);
        Integer len=0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        out.flush();
        response.flushBuffer();
        is.close();
        out.close();

    }


    //统计结果下载
    @RequestMapping("organizer/getResult")
    @ResponseBody
    public void getResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String meetingId=request.getParameter("meetingId");
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename="+new String( "统计结果.xls".getBytes("utf-8"), "ISO8859-1" ));

        //获取数据
        List<Selector> selectorList = organizerMapper.getResultByMeetingId(meetingId);
        //固定数据保存行列
        List<List<String>> rows = CollUtil.newArrayList();
        rows.add(CollUtil.newArrayList("Ranked", "Name", "Total"));
        int i=0;
        for (Selector selector: selectorList) {
            rows.add(CollUtil.newArrayList((++i)+"",selector.getName(),selector.getSum()));
        }

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter();
        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //写出到流
        writer.flush(response.getOutputStream());
        //关闭writer，释放内存
        writer.close();
    }

}
