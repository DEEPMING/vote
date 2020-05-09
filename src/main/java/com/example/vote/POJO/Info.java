package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail:  信息实体类
 */
public class Info {
    private int id;
    private String meetingId;
    private String content;
    private String classs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return id == info.id &&
                Objects.equals(meetingId, info.meetingId) &&
                Objects.equals(content, info.content) &&
                Objects.equals(classs, info.classs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingId, content, classs);
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", meetingId='" + meetingId + '\'' +
                ", content='" + content + '\'' +
                ", classs='" + classs + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public Info(int id, String meetingId, String content, String classs) {
        this.id = id;
        this.meetingId = meetingId;
        this.content = content;
        this.classs = classs;
    }

    public Info() {
    }
}
