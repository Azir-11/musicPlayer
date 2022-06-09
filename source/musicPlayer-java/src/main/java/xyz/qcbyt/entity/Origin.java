package xyz.qcbyt.entity;

/**
 *  籍贯表 实体类
 */
public class Origin {
    private Integer id;
    private String originname;

    public Origin() {
    }

    public Origin(Integer id, String originname) {
        this.id = id;
        this.originname = originname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginname() {
        return originname;
    }

    public void setOriginname(String originname) {
        this.originname = originname;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "id=" + id +
                ", originname='" + originname + '\'' +
                '}';
    }
}
