package com.example.asm01.entity;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

    public Role() {
    }
    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
