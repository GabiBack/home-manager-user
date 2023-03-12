package com.example.homemanageruser.model;

public class User {

    private Long id;
    private String userName;
    private String groupName;
    private Boolean isAdmin;

    public User() {
    }

    public User(Long id, String userName, String groupName, Boolean isAdmin) {
        this.id = id;
        this.userName = userName;
        this.groupName = groupName;
        this.isAdmin = isAdmin;
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
