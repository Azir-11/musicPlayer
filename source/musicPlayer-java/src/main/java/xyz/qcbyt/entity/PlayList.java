package xyz.qcbyt.entity;

import java.util.List;

/**
 *  gedan  歌单实体类
 */
public class PlayList {
    private Integer id;
    private String playlistname;//歌单名称
    private String profile; //歌单简介
    private String coverimg; //歌单封面图片 src
    private List<Integer> musics; //一个歌单里面的 多首歌曲; 昨天想到这里！！！！！！！！！！！！！！！！！！！list 和varchar 转换问题

    private PlayListSmall playListSmall;//一对一关系

    public PlayList(Integer id, String playlistname, String profile, String coverimg, List<Integer> musics, PlayListSmall playListSmall) {
        this.id = id;
        this.playlistname = playlistname;
        this.profile = profile;
        this.coverimg = coverimg;
        this.musics = musics;
        this.playListSmall = playListSmall;
    }

    public PlayList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public PlayListSmall getPlayListSmall() {
        return playListSmall;
    }

    public void setPlayListSmall(PlayListSmall playListSmall) {
        this.playListSmall = playListSmall;
    }

    public void setPlaylistname(String playlistname) {
        this.playlistname = playlistname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public List<Integer> getMusics() {
        return musics;
    }

    public void setMusics(List<Integer> musics) {
        this.musics = musics;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", playlistname='" + playlistname + '\'' +
                ", profile='" + profile + '\'' +
                ", coverimg='" + coverimg + '\'' +
                ", musics=" + musics +
                ", playListSmall=" + playListSmall +
                '}';
    }
}
