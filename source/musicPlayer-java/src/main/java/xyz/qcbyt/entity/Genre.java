package xyz.qcbyt.entity;

/**
 *  genre_type  流派表 实体类
 */
public class Genre {
    private Integer id;
    private String genre;

    public Genre(Integer id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }
}
