package xyz.qcbyt.entity;

/**
 * lrc  歌词表   实体类
 */
public class Lrc {
    private Integer id;
    private String lrc;
    private String lrcpath;

    public Lrc() {
    }

    public Lrc(Integer id, String lrc, String lrcpath) {
        this.id = id;
        this.lrc = lrc;
        this.lrcpath = lrcpath;
    }

    public Integer getId() {
        return id;
    }

    public String getLrcpath() {
        return lrcpath;
    }

    public void setLrcpath(String lrcpath) {
        this.lrcpath = lrcpath;
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
                ", lrcpath='" + lrcpath + '\'' +
                '}';
    }
}
