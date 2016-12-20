package sys.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "resources_role")
public class ResourcesRoleKey implements Serializable {

    private static final long serialVersionUID = 6635158511195896351L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="resc_id")
    private Integer resc_id;
//    如果使用驼峰命名 rescId,那么在查询语句需要 resc_id as rescId,或者在resultMap中 将数据库column与model中property名称进行匹配
//    @Column(name ="resc_id")  此注释只在 dao层去操作数据库的方向上，mybatis进行转换

    @Column(name ="role_id")
    private Integer role_id;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResc_id() {
        return resc_id;
    }

    public void setResc_id(Integer resc_id) {
        this.resc_id = resc_id;
    }
}