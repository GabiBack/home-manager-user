package com.example.homemanageruser.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    public UserEntity(String userName, String groupName, String password, String email, Boolean isAdmin) {
        this.userName = userName;
        this.groupName = groupName;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "is_Admin")
    private Boolean isAdmin;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public static UserEntityBuilder builder(){
        return new UserEntityBuilder();
    }

    public static class UserEntityBuilder {

        private String userName;
        private String groupName;
        private String password;
        private String email;
        private Boolean isAdmin;

        public UserEntityBuilder setUserName(final String userName){
            this.userName = userName;
            return this;
        }

        public UserEntityBuilder setGroupName(final String groupName){
            this.groupName = groupName;
            return this;
        }

        public UserEntityBuilder setPassword(final String password){
            this.password = password;
            return this;
        }

        public UserEntityBuilder setEmail(final String email){
            this.email = email;
            return this;
        }

        public UserEntityBuilder isAdmin(final Boolean isAdmin){
            this.isAdmin = isAdmin;
            return this;
        }

        public UserEntity build(){
            return new UserEntity(
                    this.userName,
                    this.groupName,
                    this.password,
                    this.email,
                    this.isAdmin);
        }
    }
}
