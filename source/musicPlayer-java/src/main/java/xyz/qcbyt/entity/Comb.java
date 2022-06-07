package xyz.qcbyt.entity;

/**
 *  comb (歌手组合表 实体类)
 */
public class Comb {
    private Integer id;
    private String combname;
    private String combprofile;

    public Comb() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCombname() {
        return combname;
    }

    public void setCombname(String combname) {
        this.combname = combname;
    }

    public String getCombprofile() {
        return combprofile;
    }

    public void setCombprofile(String combprofile) {
        this.combprofile = combprofile;
    }

    public Comb(Integer id, String combname, String combprofile) {
        this.id = id;
        this.combname = combname;
        this.combprofile = combprofile;
    }

    @Override
    public String toString() {
        return "Comb{" +
                "id=" + id +
                ", combname='" + combname + '\'' +
                ", combprofile='" + combprofile + '\'' +
                '}';
    }
}
