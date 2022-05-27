package xyz.qcbyt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {
    private Integer id;
    @NotBlank(message = "⽤户名不能为空!")
    @NotNull
    @Length(min=2,max=10,message = "用户名长度至少2位，最长10位")
    private String username;
    @NotBlank(message = "⽤户密码不能为空!")
    @Length(min = 6, max = 10,message = "密码⻓度⾄少6位但不超过10位!")
    @NotNull
    private String password;
    private Integer role;//用户权限 默认 0
    @Email(message = "邮箱格式不正确")
    @Length(min = 15,max = 20,message = "邮箱至少15位,最多20位")
    private String email;
    private String profile;//用户简介

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registertime;//注册时间
    private Integer totallogin;//总共登录天数

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recentlogin;//用户最近一次登录时间

    public User() {
    }

    public User(Integer id, String username, String password, Integer role, String email, String profile, Date registertime, Integer totallogin, Date recentlogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.profile = profile;
        this.registertime = registertime;
        this.totallogin = totallogin;
        this.recentlogin = recentlogin;
    }

    public Date getRecentlogin() {
        return recentlogin;
    }

    public void setRecentlogin(Date recentlogin) {
        this.recentlogin = recentlogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", profile='" + profile + '\'' +
                ", registertime=" + registertime +
                ", totallogin=" + totallogin +
                ", recentlogin=" + recentlogin +
                '}';
    }
}
