package sys.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="resources")
public class Resources implements Serializable {

    private static final long serialVersionUID = -2967538600786536621L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "parentId")
    private Integer parentId;

    @Column(name = "resKey")
    private String resKey;

    @Column(name = "type")
    private String type;

    @Column(name = "resUrl")
    private String resUrl;

    @Column(name = "level")
    private Integer level;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resources(Integer id, String name, Integer parentid, String resKey, String type, String resUrl, Integer level, String description) {
        this.id = id;
        this.name = name;
        this.parentId = parentid;
        this.resKey = resKey;
        this.type = type;
        this.resUrl = resUrl;
        this.level = level;
        this.description = description;
    }

    public Resources() {
    }

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", resKey='" + resKey + '\'' +
                ", type='" + type + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                '}';
    }
}