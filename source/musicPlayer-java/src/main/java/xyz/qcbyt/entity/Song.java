package xyz.qcbyt.entity;

public class Song {
    private Integer id;
    private String  musicname; //歌曲名
    private String  singername;//歌手 名字
    private String  albumname;//专辑名字
    private Integer  duration; //歌曲时长 按秒记录
    private String  songurl;  //歌曲文件地址
    private String  lrcurl;   //歌词文件地址
    private String  coverimg; //歌曲封面图片 地址

    public Song() {
    }

    public Song(Integer id, String musicname, String singername, String albumname, Integer duration, String songurl, String lrcurl, String coverimg) {
        this.id = id;
        this.musicname = musicname;
        this.singername = singername;
        this.albumname = albumname;
        this.duration = duration;
        this.songurl = songurl;
        this.lrcurl = lrcurl;
        this.coverimg = coverimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl) {
        this.songurl = songurl;
    }

    public String getLrcurl() {
        return lrcurl;
    }

    public void setLrcurl(String lrcurl) {
        this.lrcurl = lrcurl;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", musicname='" + musicname + '\'' +
                ", singername='" + singername + '\'' +
                ", albumname='" + albumname + '\'' +
                ", duration='" + duration + '\'' +
                ", songurl='" + songurl + '\'' +
                ", lrcurl='" + lrcurl + '\'' +
                ", coverimg='" + coverimg + '\'' +
                '}';
    }
}
