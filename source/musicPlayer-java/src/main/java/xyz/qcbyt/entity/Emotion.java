package xyz.qcbyt.entity;

/**
 * emotion_type 情感表 实体类
 */
public class Emotion {
    private Integer id;
    private String emotion;

    public Emotion(Integer id, String emotion) {
        this.id = id;
        this.emotion = emotion;
    }

    public Emotion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    @Override
    public String toString() {
        return "Emotion{" +
                "id=" + id +
                ", emotion='" + emotion + '\'' +
                '}';
    }
}
