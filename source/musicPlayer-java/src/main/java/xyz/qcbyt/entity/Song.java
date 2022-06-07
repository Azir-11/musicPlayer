package xyz.qcbyt.entity;

import java.util.Date;

/**
 * song （歌曲实体类）
 */
public class Song {
    private Integer id;
    private String musicname; //音乐名称
    private Integer singerid;   //歌手id
    private String songwriter;  //词作家
    private String composer;    //曲作家
    private Date reltime;       //歌曲发行时间
    private String coverimg;    //封面图片地址
    private String mp3src;      //音乐文件地址
    private Integer lrcid;      //歌词id

    private Boolean auditstatus;//审核页 显示的一首歌的审核状态
    private Boolean likestatus;
    private Integer userid;   //用户id (上传到审核表的时候用)

    private Lrc lrc;//一对一映射 歌词表
    public Song() {
    }

    public Song(Integer id, String musicname, Integer singerid, String songwriter, String composer, Date reltime, String coverimg, String mp3src, Integer lrcid, Boolean auditstatus, Boolean likestatus, Integer userid, Lrc lrc) {
        this.id = id;
        this.musicname = musicname;
        this.singerid = singerid;
        this.songwriter = songwriter;
        this.composer = composer;
        this.reltime = reltime;
        this.coverimg = coverimg;
        this.mp3src = mp3src;
        this.lrcid = lrcid;
        this.auditstatus = auditstatus;
        this.likestatus = likestatus;
        this.userid = userid;
        this.lrc = lrc;
    }

    public Lrc getLrc() {
        return lrc;
    }

    public void setLrc(Lrc lrc) {
        this.lrc = lrc;
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

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public String getSongwriter() {
        return songwriter;
    }

    public void setSongwriter(String songwriter) {
        this.songwriter = songwriter;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Date getReltime() {
        return reltime;
    }

    public void setReltime(Date reltime) {
        this.reltime = reltime;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public String getMp3src() {
        return mp3src;
    }

    public void setMp3src(String mp3src) {
        this.mp3src = mp3src;
    }

    public Integer getLrcid() {
        return lrcid;
    }

    public void setLrcid(Integer lrcid) {
        this.lrcid = lrcid;
    }

    public Boolean getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Boolean auditstatus) {
        this.auditstatus = auditstatus;
    }

    public Boolean getLikestatus() {
        return likestatus;
    }

    public void setLikestatus(Boolean likestatus) {
        this.likestatus = likestatus;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", musicname='" + musicname + '\'' +
                ", singerid=" + singerid +
                ", songwriter='" + songwriter + '\'' +
                ", composer='" + composer + '\'' +
                ", reltime=" + reltime +
                ", coverimg='" + coverimg + '\'' +
                ", mp3src='" + mp3src + '\'' +
                ", lrcid=" + lrcid +
                ", auditstatus=" + auditstatus +
                ", likestatus=" + likestatus +
                ", userid=" + userid +
                ", lrc=" + lrc +
                '}';
    }
}
