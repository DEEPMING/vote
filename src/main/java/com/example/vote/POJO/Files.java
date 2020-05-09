package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 文件信息 实体类
 */
public class Files {
    private int id;
    private String meetingId;
    private String fileName;
    private String filePath;
    private String fileClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Files files = (Files) o;
        return id == files.id &&
                Objects.equals(meetingId, files.meetingId) &&
                Objects.equals(fileName, files.fileName) &&
                Objects.equals(filePath, files.filePath) &&
                Objects.equals(fileClass, files.fileClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingId, fileName, filePath, fileClass);
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", meetingId='" + meetingId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileClass='" + fileClass + '\'' +
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileClass() {
        return fileClass;
    }

    public void setFileClass(String fileClass) {
        this.fileClass = fileClass;
    }

    public Files(int id, String meetingId, String fileName, String filePath, String fileClass) {
        this.id = id;
        this.meetingId = meetingId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileClass = fileClass;
    }

    public Files() {
    }
}
