package com.example.vote.controller;

import com.alibaba.fastjson.JSON;
import com.example.vote.POJO.Personnel;
import com.example.vote.POJO.Personnel_login;
import com.example.vote.mapper.LoginMapper;
import com.example.vote.mapper.RegisteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author: Jerry
 * Time: 3. 8
 * Detail:
 */
@Controller
public class LoginController {
    @Autowired
    RegisteMapper registeMapper; //注册 数据库操作

    @Autowired
    LoginMapper loginMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    Map<String, String> resultMap = new TreeMap<>();
    String result = new String();
    String msg = new String();
    Personnel personnel;

    //获取登陆页面
    @RequestMapping("index")
    public String getLoginIndex(){
        logger.info("访问login/index");
        return "/login/login.html";
    }

    //验证登陆名、密码、身份
    @RequestMapping("check")
    @ResponseBody
    public String checkLoginInfo(HttpServletRequest request){
        logger.info("访问login/check");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String userIdentity = request.getParameter("userIdentity");

        if("".equals(userName) || "".equals(userPassword) || "".equals(userIdentity)) {
            result = "-1";
            msg = "输入项不能为空";
        }
        Boolean checkOut = false;
        if("0".equals(userIdentity)){
            personnel = new Personnel();
            personnel.setEmail(userName);
            personnel.setPassword(userPassword);
            personnel = loginMapper.verifyOrganizer(personnel);
            if(personnel != null){
                request.getSession().setAttribute("personnel", personnel);
                checkOut = true;
            }
        }else if("4".equals(userIdentity)){ //系统管理员
            personnel = new Personnel();
            personnel.setEmail(userName);
            personnel.setPassword(userPassword);
            personnel = loginMapper.verifySystem(personnel);
            if(personnel != null){
                request.getSession().setAttribute("personnel", personnel);
                checkOut = true;
            }
        }else {
            Personnel_login personnelLogin = new Personnel_login();
            personnelLogin.setUserName(userName);
            personnelLogin.setPassword(userPassword);
            personnelLogin.setIdentity(userIdentity);
            personnelLogin = loginMapper.verifyOthers(personnelLogin);
            if(personnelLogin != null){
                request.getSession().setAttribute("personnel", personnelLogin);
                checkOut = true;
            }
        }
        if(checkOut == false){
            result = "-2";
            msg = "登陆信息错误";
        }else
        if(checkOut && "0".equals(userIdentity)){
            result = "0";
            msg = "./organizer/index";
        }else
        if(checkOut && "1".equals(userIdentity)){
            result = "1";
            msg = "./participator/index";
        }else
        if(checkOut && "2".equals(userIdentity)){
            result = "2";
            msg = "./observer/index";
        }else
        if(checkOut && "3".equals(userIdentity)){
            result = "3";
            msg = "./observer/index";
        }else
        if(checkOut && "4".equals(userIdentity)){
            result = "3";
            msg = "./system/index";
        }

        resultMap.put("result", result);
        resultMap.put("msg", msg);
        String json = JSON.toJSONString(resultMap);
        return json;
    }

    //获取注册页面
    @RequestMapping("registe")
    public String getRegistePage(){
        logger.info("访问login/registe");
        return "/login/register.html";
    }
    //注册信息录入
    @RequestMapping("registeInfo")
    @ResponseBody
    public String saveRegisteInfo(HttpServletRequest request){
        logger.info("访问login/registeInfo");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");
        String organName = request.getParameter("organName");

        if("".equals(email) || "".equals(userPassword) || "".equals(organName)){
            result = "-1";
            msg = "注册信息不能为空";
        }else{
            personnel = new Personnel();
            personnel.setEmail(email);
            personnel.setName(organName);
            personnel.setPassword(userPassword);
            personnel.setIdentity("0");
            personnel.setAddress("-1");

            Personnel personnel1 = registeMapper.getPersonnel(personnel);
            if(personnel1 != null){
                result = "1";
                msg = "邮箱已注册";
            }else{
                int res = registeMapper.addPersonnel(personnel);
                if(res>0){
                    result = "1";
                    msg = "注册成功";
                }else{
                    result = "0";
                    msg = "注册失败";
                }
            }
        }

        resultMap.put("result", result);
        resultMap.put("msg", msg);
        String json = JSON.toJSONString(resultMap);
        return json;
    }
}
