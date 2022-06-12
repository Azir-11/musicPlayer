package xyz.qcbyt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 *  个人喜欢表 实体类
 */
public class FavMusic {
    private Integer id;
    private String coverimg;
    private List<Integer> musiclist;
    private Integer userid;

    public FavMusic() {
    }

    public FavMusic(Integer id, String coverimg, List<Integer> musiclist, Integer userid) {
        this.id = id;
        this.coverimg = coverimg;
        this.musiclist = musiclist;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public List<Integer> getMusiclist() {
        return musiclist;
    }

    public void setMusiclist(List<Integer> musiclist) {
        this.musiclist = musiclist;
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
                ", coverimg='" + coverimg + '\'' +
                ", musiclist=" + musiclist +
                ", userid=" + userid +
                '}';
    }
}
