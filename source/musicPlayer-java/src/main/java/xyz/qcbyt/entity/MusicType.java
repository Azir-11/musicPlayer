package xyz.qcbyt.entity;

/**
 * musictype  (音乐分类表) 实体类
 */
public class MusicType {
    private Integer id;
    private Integer songid;    //歌曲id
    private Integer languageid;//语种id
    private Integer genreid;    //流派id
    private Integer topicid;    //主题id
    private Integer emotionid ; //情感id
    private Integer sceneid ;   //场景id
    //一对一映射
    private Song song;
    private Language language;
    private Genre genre;
    private Topic topic;
    private Emotion emotion;
    private Scene scene;

    public MusicType() {
    }

    public MusicType(Integer id, Integer songid, Integer languageid, Integer genreid, Integer topicid, Integer emotionid, Integer sceneid, Song song, Language language, Genre genre, Topic topic, Emotion emotion, Scene scene) {
        this.id = id;
        this.songid = songid;
        this.languageid = languageid;
        this.genreid = genreid;
        this.topicid = topicid;
        this.emotionid = emotionid;
        this.sceneid = sceneid;
        this.song = song;
        this.language = language;
        this.genre = genre;
        this.topic = topic;
        this.emotion = emotion;
        this.scene = scene;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongid() {
        return songid;
    }

    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    public Integer getLanguageid() {
        return languageid;
    }

    public void setLanguageid(Integer languageid) {
        this.languageid = languageid;
    }

    public Integer getGenreid() {
        return genreid;
    }

    public void setGenreid(Integer genreid) {
        this.genreid = genreid;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public Integer getEmotionid() {
        return emotionid;
    }

    public void setEmotionid(Integer emotionid) {
        this.emotionid = emotionid;
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "MusicType{" +
                "id=" + id +
                ", songid=" + songid +
                ", languageid=" + languageid +
                ", genreid=" + genreid +
                ", topicid=" + topicid +
                ", emotionid=" + emotionid +
                ", sceneid=" + sceneid +
                ", song=" + song +
                ", language=" + language +
                ", genre=" + genre +
                ", topic=" + topic +
                ", emotion=" + emotion +
                ", scene=" + scene +
                '}';
    }
}
