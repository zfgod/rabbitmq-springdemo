package sys.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_role")
public class UserRoleKey implements Serializable {

    private static final long serialVersionUID = 4468527067463947132L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_id")
    private Long user_id;

    @Column(name="role_id")
    private Long role_id;

    public UserRoleKey() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}