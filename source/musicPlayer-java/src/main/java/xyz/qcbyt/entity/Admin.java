package xyz.qcbyt.entity;

/**
 * admin 管理员 实体类
 */
public class Admin {
    private Integer id;
    private String adminnum; //工号 （管理员登录用）
    private Integer level;  //权限等级
    private String name;    //管理员姓名
    private String password; //密码
    private String phone; //电话
    private String email;
    private String sex;
    private String idcard; //身份证号
    private String coverimg; //头像

    public Admin() {
    }

    public Admin(Integer id, String adminnum, Integer level, String name, String password, String phone, String email, String sex, String idcard, String coverimg) {
        this.id = id;
        this.adminnum = adminnum;
        this.level = level;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.idcard = idcard;
        this.coverimg = coverimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminnum() {
        return adminnum;
    }

    public void setAdminnum(String adminnum) {
        this.adminnum = adminnum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminnum='" + adminnum + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", idcard='" + idcard + '\'' +
                ", coverimg='" + coverimg + '\'' +
                '}';
    }
}
