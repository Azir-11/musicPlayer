package xyz.qcbyt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用户 喜欢的歌曲页面 的 实体类
 */
public class UserLoveMusic {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lovemoment;
    private Integer musicid;
    private Integer userid;
//    关联查询
    private Song song;


    public UserLoveMusic() {
    }

    public UserLoveMusic(Integer id, Date lovemoment, Integer musicid, Integer userid, Song song) {
        this.id = id;
        this.lovemoment = lovemoment;
        this.musicid = musicid;
        this.userid = userid;
        this.song = song;

    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
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

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }


    @Override
    public String toString() {
        return "UserLoveMusic{" +
                "id=" + id +
                ", lovemoment=" + lovemoment +
                ", musicid=" + musicid +
                ", userid=" + userid +
                ", song=" + song +
                '}';
    }
}
