package xyz.qcbyt.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * song （歌曲实体类）
 */
public class Song {
    private Integer id;
    @NotBlank(message = "音乐名称为必传项")
    @Length(max = 10)
    private String musicname; //音乐名称
    private Integer singerid;   //歌手id
    private String songwriter;  //词作家
    private String composer;    //曲作家
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String reltime;
    private String coverimg;    //封面图片地址
    @NotBlank(message = "MP3文件为必传项")
    private String mp3src;      //音乐文件地址
    @NotNull(message = "Lrcid为必传项")
    private Integer lrcid;      //歌词id
    private Integer duration; //歌曲时长 秒数

    private Integer languageid; //语种id
    private Integer sceneid;    //场景id
    private Integer emotionid;  //情感id
    private Integer topicid;    //主题id

    //一对一
    private Language language;
    private Scene scene;
    private Emotion emotion;
    private Topic topic;


    private Boolean auditstatus;//审核页 显示的一首歌的审核状态
    private Boolean likestatus;
    @NotNull(message = "userid为必传项")
    private Integer userid;   //用户id (上传到审核表的时候用)


    private Lrc lrc;//一对一映射 歌词表
    private Singer singer;  //歌手表
    public Song() {
    }

    public Song(Integer id, String musicname, Integer singerid, String songwriter, String composer, String reltime, String coverimg, String mp3src, Integer lrcid, Integer duration, Integer languageid, Integer sceneid, Integer emotionid, Integer topicid, Language language, Scene scene, Emotion emotion, Topic topic, Boolean auditstatus, Boolean likestatus, Integer userid, Lrc lrc, Singer singer) {
        this.id = id;
        this.musicname = musicname;
        this.singerid = singerid;
        this.songwriter = songwriter;
        this.composer = composer;
        this.reltime = reltime;
        this.coverimg = coverimg;
        this.mp3src = mp3src;
        this.lrcid = lrcid;
        this.duration = duration;
        this.languageid = languageid;
        this.sceneid = sceneid;
        this.emotionid = emotionid;
        this.topicid = topicid;
        this.language = language;
        this.scene = scene;
        this.emotion = emotion;
        this.topic = topic;
        this.auditstatus = auditstatus;
        this.likestatus = likestatus;
        this.userid = userid;
        this.lrc = lrc;
        this.singer = singer;
    }

    public Lrc getLrc() {
        return lrc;
    }

    public Singer getSinger() {
        return singer;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getLanguageid() {
        return languageid;
    }

    public void setLanguageid(Integer languageid) {
        this.languageid = languageid;
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }

    public Integer getEmotionid() {
        return emotionid;
    }

    public void setEmotionid(Integer emotionid) {
        this.emotionid = emotionid;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public void setLrc(Lrc lrc) {
        this.lrc = lrc;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getReltime() {
        return reltime;
    }

    public void setReltime(String reltime) {
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
                ", duration=" + duration +
                ", languageid=" + languageid +
                ", sceneid=" + sceneid +
                ", emotionid=" + emotionid +
                ", topicid=" + topicid +
                ", language=" + language +
                ", scene=" + scene +
                ", emotion=" + emotion +
                ", topic=" + topic +
                ", auditstatus=" + auditstatus +
                ", likestatus=" + likestatus +
                ", userid=" + userid +
                ", lrc=" + lrc +
                ", singer=" + singer +
                '}';
    }
}
