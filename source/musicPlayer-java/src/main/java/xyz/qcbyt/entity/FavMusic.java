package xyz.qcbyt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 *  个人喜欢表 实体类
 */
public class FavMusic {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lovemoment;
    private Integer musicid;
    private Integer userid;

    public FavMusic() {
    }

    public FavMusic(Integer id, Date lovemoment, Integer musicid, Integer userid) {
        this.id = id;
        this.lovemoment = lovemoment;
        this.musicid = musicid;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLovemoment() {
        return lovemoment;
    }

    public void setLovemoment(Date lovemoment) {
        this.lovemoment = lovemoment;
    }

    public Integer getMusicid() {
        return musicid;
    }

    public void setMusicid(Integer musicid) {
        this.musicid = musicid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "FavMusic{" +
                "id=" + id +
                ", lovemoment=" + lovemoment +
                ", musicid=" + musicid +
                ", userid=" + userid +
                '}';
    }
}
