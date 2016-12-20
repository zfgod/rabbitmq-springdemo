package sys.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = -5265040781149288583L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "userName")
    private String userName;

    @Column(name="userPassword")
    @JsonIgnore
    private String userPassword;

    @Column(name = "userRealName")
    private String userRealName;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userQQ")
    private String userQQ;

    @Column(name = "regTime")
    private Date regTime;

    @Column(name = "status")
    private String status;
    @Transient
    private List<Integer> roleList;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userQQ='" + userQQ + '\'' +
                ", regTime=" + regTime +
                ", status='" + status + '\'' +
                '}';
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}