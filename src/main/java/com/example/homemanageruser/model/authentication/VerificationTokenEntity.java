package com.example.homemanageruser.model.authentication;

import com.example.homemanageruser.defaults.AuthenticationDefaults;
import com.example.homemanageruser.model.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tokens")
public class VerificationTokenEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public VerificationTokenEntity(String token, UserEntity userEntity) {
        super();
        this.token = token;
        this.userEntity = userEntity;
        this.expirationTime = calculateExpirationDate(AuthenticationDefaults.EXPIRATION_TIME);
    }

    public VerificationTokenEntity(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(AuthenticationDefaults.EXPIRATION_TIME);
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);

        return new Date(calendar.getTime().getTime());
    }
}
