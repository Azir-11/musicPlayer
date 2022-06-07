package xyz.qcbyt.entity;

/**
 * lrc  歌词表   实体类
 */
public class Lrc {
    private Integer id;
    private String lrc;

    public Lrc() {
    }

    public Lrc(Integer id, String lrc) {
        this.id = id;
        this.lrc = lrc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    @Override
    public String toString() {
        return "Lrc{" +
                "id=" + id +
                ", lrc='" + lrc + '\'' +
                '}';
    }
}
