package com.example.homemanageruser.model.user;

import com.example.homemanageruser.model.authentication.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {

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

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;


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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
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
