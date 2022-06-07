package xyz.qcbyt.entity;

/**
 * topic_type   主题表 实体类
 */
public class Topic {
    private Integer id;
    private String topic;

    public Topic() {
    }

    public Topic(Integer id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                '}';
    }
}
