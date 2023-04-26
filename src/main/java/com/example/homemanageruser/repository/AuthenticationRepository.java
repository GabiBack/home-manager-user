package com.example.homemanageruser.repository;

import com.example.homemanageruser.model.authentication.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<VerificationTokenEntity, Long> {

    VerificationTokenEntity findByToken(String token);

}
