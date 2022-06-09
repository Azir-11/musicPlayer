package xyz.qcbyt.entity;

/**
 * language_type (语种实体类)
 */
public class Language {
    private Integer id;
    private String language;

    public Language(Integer id, String language) {
        this.id = id;
        this.language = language;
    }

    public Language() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", language='" + language + '\'' +
                '}';
    }
}
