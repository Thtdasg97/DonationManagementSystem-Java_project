package com.example.asm01.entity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="full_name")
    private String fullName;
    @Column(name="note")
    private String note;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="status")
    private int status;
    @Column(name="user_name")
    private String userName;
    @Column(name="address")
    private String address;
    @Column(name="password")
    private String password;
    @Column(name="created")
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade =
            {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST})
    List<UserDonation> userDonations;

    public User() {
    }

    public User(String fullName, String note, String email, String phoneNumber, int status, String userName, String address, String password, Date createdAt, Role role) {
        this.fullName = fullName;
        this.note = note;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.createdAt = createdAt;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserDonation> getUserDonations() {
        return userDonations;
    }

    public void setUserDonations(List<UserDonation> userDonations) {
        this.userDonations = userDonations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
