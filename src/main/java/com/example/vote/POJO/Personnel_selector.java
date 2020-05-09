package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 参与人及选项关系 实体类
 */
public class Personnel_selector {
    private int id;
    private String mainId;
    private String selectorId;

    @Override
    public String toString() {
        return "Personnel_selector{" +
                "id=" + id +
                ", mainId='" + mainId + '\'' +
                ", selectorId='" + selectorId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnel_selector that = (Personnel_selector) o;
        return id == that.id &&
                Objects.equals(mainId, that.mainId) &&
                Objects.equals(selectorId, that.selectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mainId, selectorId);
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

    public String getSelectorId() {
        return selectorId;
    }

    public void setSelectorId(String selectorId) {
        this.selectorId = selectorId;
    }

    public Personnel_selector(int id, String mainId, String selectorId) {
        this.id = id;
        this.mainId = mainId;
        this.selectorId = selectorId;
    }

    public Personnel_selector() {
    }
}
