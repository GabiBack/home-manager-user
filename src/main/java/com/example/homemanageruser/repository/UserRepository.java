package com.example.homemanageruser.repository;

import com.example.homemanageruser.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.userName = ?1 AND u.groupName = ?2")
    UserEntity findUserInGroupByUserName(String userName, String groupName);

}
