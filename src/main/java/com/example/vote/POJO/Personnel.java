package com.example.vote.POJO;

import java.util.Objects;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 用户信息 实体类
 */
public class Personnel {
    private int id;
    private String email;
    private String name;
    private String password;
    private String identity;
    private String up;
    private String code;
    private String address;
    private String identityCode;
    private String beizhu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnel personnel = (Personnel) o;
        return id == personnel.id &&
                Objects.equals(email, personnel.email) &&
                Objects.equals(name, personnel.name) &&
                Objects.equals(password, personnel.password) &&
                Objects.equals(identity, personnel.identity) &&
                Objects.equals(up, personnel.up) &&
                Objects.equals(code, personnel.code) &&
                Objects.equals(address, personnel.address) &&
                Objects.equals(identityCode, personnel.identityCode) &&
                Objects.equals(beizhu, personnel.beizhu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, identity, up, code, address, identityCode, beizhu);
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", up='" + up + '\'' +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", beizhu='" + beizhu + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Personnel(int id, String email, String name, String password, String identity, String up, String code, String address, String identityCode, String beizhu) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.identity = identity;
        this.up = up;
        this.code = code;
        this.address = address;
        this.identityCode = identityCode;
        this.beizhu = beizhu;
    }

    public Personnel() {
    }
}
