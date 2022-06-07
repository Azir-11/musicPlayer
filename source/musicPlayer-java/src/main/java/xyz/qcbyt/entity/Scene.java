package xyz.qcbyt.entity;

/**
 * scene_type 场景表实体类
 */
public class Scene {
    private Integer id;
    private String scene;

    public Scene() {
    }

    public Scene(Integer id, String scene) {
        this.id = id;
        this.scene = scene;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", scene='" + scene + '\'' +
                '}';
    }
}
