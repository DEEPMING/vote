package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 会议基本信息 实体类
 */
public class Meeting {
    private int id;
    private String code;
    private String name;
    private String time;
    private String address;
    private String organizer;
    private String mainId;

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", organizer='" + organizer + '\'' +
                ", mainId='" + mainId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                Objects.equals(code, meeting.code) &&
                Objects.equals(name, meeting.name) &&
                Objects.equals(time, meeting.time) &&
                Objects.equals(address, meeting.address) &&
                Objects.equals(organizer, meeting.organizer) &&
                Objects.equals(mainId, meeting.mainId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, time, address, organizer, mainId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Meeting(int id, String code, String name, String time, String address, String organizer, String mainId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.time = time;
        this.address = address;
        this.organizer = organizer;
        this.mainId = mainId;
    }

    public Meeting() {
    }
}
