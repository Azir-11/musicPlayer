package xyz.qcbyt.entity;

/**
 * band (歌手乐队表 实体类)
 */
public class Band {
    private Integer id;
    private String bandname;
    private String bandprofile;

    public Band(Integer id, String bandname, String bandprofile) {
        this.id = id;
        this.bandname = bandname;
        this.bandprofile = bandprofile;
    }

    public Band() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBandname() {
        return bandname;
    }

    public void setBandname(String bandname) {
        this.bandname = bandname;
    }

    public String getBandprofile() {
        return bandprofile;
    }

    public void setBandprofile(String bandprofile) {
        this.bandprofile = bandprofile;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", bandname='" + bandname + '\'' +
                ", bandprofile='" + bandprofile + '\'' +
                '}';
    }
}
