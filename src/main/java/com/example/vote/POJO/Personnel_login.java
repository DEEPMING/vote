package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 用户登陆信息 实体类
 * 除组织者外，其他用户在此验证
 */
public class Personnel_login {
    private int id;
    private String mainId;
    private String userName;
    private String password;
    private String identity;
    private String meetingId;
    private String finished;

    @Override
    public String toString() {
        return "Personnel_login{" +
                "id=" + id +
                ", mainId='" + mainId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", finished='" + finished + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public Personnel_login() {
    }

    public Personnel_login(int id, String mainId, String userName, String password, String identity, String meetingId, String finished) {
        this.id = id;
        this.mainId = mainId;
        this.userName = userName;
        this.password = password;
        this.identity = identity;
        this.meetingId = meetingId;
        this.finished = finished;
    }
}
