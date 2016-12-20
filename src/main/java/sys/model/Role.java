package sys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = -4009171958775925270L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "roleKey")
    private String roleKey;

    @Column(name = "description")
    private String description;
    
    @Column(name = "enable")
    private Integer enable;

// 绑定权限
    @Transient
    private List<Integer> resList;



    public Role() {

    }
    public Role(Integer id, String name, String roleKey, String description, Integer enable) {
        this.id = id;
        this.name = name;
        this.roleKey = roleKey;
        this.description = description;
        this.enable = enable;
    }

    public List<Integer> getResList() {
        return resList;
    }

    public void setResList(List<Integer> resList) {
        this.resList = resList;
    }

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

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}