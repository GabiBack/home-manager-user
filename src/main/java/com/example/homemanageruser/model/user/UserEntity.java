package com.example.homemanageruser.model.user;

import com.example.homemanageruser.model.authentication.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity /*implements UserDetails */{

    public UserEntity(String userName, String groupName, String password, String email, Role role) {
        this.userName = userName;
        this.groupName = groupName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "enabled")
    private boolean enabled = false;


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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserEntityBuilder builder(){
        return new UserEntityBuilder();
    }

    public static class UserEntityBuilder {

        private String userName;
        private String groupName;
        private String password;
        private String email;
        private Role role;

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

        public UserEntityBuilder setRole(final Role role){
            this.role = role;
            return this;
        }

        public UserEntity build(){
            return new UserEntity(
                    this.userName,
                    this.groupName,
                    this.password,
                    this.email,
                    this.role);
        }
    }
}
