package com.yang.furniture.entity;

/**
 * @author 刘洋
 * @date 2022/5/22  3:19 PM
 */
public class Member {
    private Integer id;
    private String username;
    private String password;
    private String email;

    private Integer role = 2;


    public Member() {
    }

    public Member(Integer id, String username, String password, String email, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        if (!(role == null || role == 0)) {
            this.role = role;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
