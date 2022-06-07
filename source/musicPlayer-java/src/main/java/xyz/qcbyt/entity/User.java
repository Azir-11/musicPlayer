package xyz.qcbyt.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * user 用户实体类
 */
public class User {
    private Integer id;
    @NotBlank(message = "真实姓名不能为空")
    @Length(min = 2,max = 10,message = "真实姓名最短2位，最长10位")
    private String name; //真实姓名
    @NotNull
    @Length(min = 2,max = 10,message = "用户名长度最短2位，最长10位")
    private String username; //用户昵称 相当于用户名 登录用
    @NotNull
    @Length(min = 6,max = 15,message = "密码的长度最短6位，最长10位")
    private String password;
    @NotNull
    @Length(min = 1,max = 2,message = "性别为男或女")
    private String sex;
    @Length(min = 11,max = 11,message = "手机号长度为11位")
    private String phone;
    @Email
    @Length(min = 15,max =25,message = "邮箱长度最短15位，最长25位")
    private String email;
    private String coverimg;    //用户头像
    private String profile;     //用户简介
    private Integer role;        //用户权限 默认为0 最低级
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registertime;   //注册时间
    private Integer totallogin;//总共登录天数
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recentlogin;    //用户最近一次登录时间

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Integer getTotallogin() {
        return totallogin;
    }

    public void setTotallogin(Integer totallogin) {
        this.totallogin = totallogin;
    }

    public Date getRecentlogin() {
        return recentlogin;
    }

    public void setRecentlogin(Date recentlogin) {
        this.recentlogin = recentlogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", coverimg='" + coverimg + '\'' +
                ", profile='" + profile + '\'' +
                ", role=" + role +
                ", registertime=" + registertime +
                ", totallogin=" + totallogin +
                ", recentlogin=" + recentlogin +
                '}';
    }

    public User(Integer id, String name, String username, String password, String sex, String phone, String email, String coverimg, String profile, Integer role, Date registertime, Integer totallogin, Date recentlogin) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.coverimg = coverimg;
        this.profile = profile;
        this.role = role;
        this.registertime = registertime;
        this.totallogin = totallogin;
        this.recentlogin = recentlogin;
    }
}
