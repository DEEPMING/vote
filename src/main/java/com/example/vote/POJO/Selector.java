package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 选项信息 实体类
 */
public class Selector {
    private int id;
    private String meetingId;
    private String name;
    private String content;
    private String sum;

    @Override
    public String toString() {
        return "Selector{" +
                "id=" + id +
                ", meetiongId='" + meetingId + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", sum='" + sum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selector selector = (Selector) o;
        return id == selector.id &&
                Objects.equals(meetingId, selector.meetingId) &&
                Objects.equals(name, selector.name) &&
                Objects.equals(content, selector.content) &&
                Objects.equals(sum, selector.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingId, name, content, sum);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Selector(int id, String meetiongId, String name, String content, String sum) {
        this.id = id;
        this.meetingId = meetiongId;
        this.name = name;
        this.content = content;
        this.sum = sum;
    }

    public Selector() {
    }
}
