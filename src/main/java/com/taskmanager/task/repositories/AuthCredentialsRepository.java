package com.taskmanager.task.repositories;

import com.taskmanager.task.pojo.AuthCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthCredentialsRepository extends JpaRepository<AuthCredentials, Integer> {
    Optional<AuthCredentials> findByUsername(String username);

    @Modifying
    @Query("UPDATE AuthCredentials t SET t.flag = false WHERE t.user.userId = :id")
    int deleteUserCredentials(@Param("id") Integer id);
    List<AuthCredentials> findAll();

}
