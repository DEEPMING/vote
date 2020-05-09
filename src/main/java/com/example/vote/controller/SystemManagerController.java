package com.example.vote.controller;

import com.example.vote.mapper.SystemManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Jerry
 * Time: 4. 4
 * Detail:
 */
@Controller
public class SystemManagerController {
    @Autowired
    SystemManagerMapper systemManagerMapper;

    @RequestMapping("system/index")
    public String getSystemIndexPage(Model model){
        model.addAttribute("personnels", systemManagerMapper.getAllAuthPersonnel());
        model.addAttribute("personnels2", systemManagerMapper.getAllNoAuthPersonnel());
        return "system/index.html";
    }


    //授权操作
    @RequestMapping("system/authOrganizer")
    public String authOrganizer(HttpServletRequest request){
        String email = request.getParameter("id");
        systemManagerMapper.authOrganizer(email);
        return "system/index.html";
    }

}
