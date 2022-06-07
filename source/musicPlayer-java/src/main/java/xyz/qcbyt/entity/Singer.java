package xyz.qcbyt.entity;

/**
 * singer (歌手表实体类)
 */
public class Singer {
    private Integer id;
    private String singer; //歌手名
    private String singerimg; //歌手头像
    private String profile;     //歌手简介
    private String sex;
    private Integer originid;   //籍贯id
    private Integer combid;     //组合id
    private Integer bandid;     //乐队id

    //一对一映射
    private Origin origin;
    private Comb comb;
    private Band band;

    public Singer() {
    }

    public Singer(Integer id, String singer, String singerimg, String profile, String sex, Integer originid, Integer combid, Integer bandid, Origin origin, Comb comb, Band band) {
        this.id = id;
        this.singer = singer;
        this.singerimg = singerimg;
        this.profile = profile;
        this.sex = sex;
        this.originid = originid;
        this.combid = combid;
        this.bandid = bandid;
        this.origin = origin;
        this.comb = comb;
        this.band = band;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSingerimg() {
        return singerimg;
    }

    public void setSingerimg(String singerimg) {
        this.singerimg = singerimg;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getOriginid() {
        return originid;
    }

    public void setOriginid(Integer originid) {
        this.originid = originid;
    }

    public Integer getCombid() {
        return combid;
    }

    public void setCombid(Integer combid) {
        this.combid = combid;
    }

    public Integer getBandid() {
        return bandid;
    }

    public void setBandid(Integer bandid) {
        this.bandid = bandid;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Comb getComb() {
        return comb;
    }

    public void setComb(Comb comb) {
        this.comb = comb;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", singer='" + singer + '\'' +
                ", singerimg='" + singerimg + '\'' +
                ", profile='" + profile + '\'' +
                ", sex='" + sex + '\'' +
                ", originid=" + originid +
                ", combid=" + combid +
                ", bandid=" + bandid +
                ", origin=" + origin +
                ", comb=" + comb +
                ", band=" + band +
                '}';
    }
}
