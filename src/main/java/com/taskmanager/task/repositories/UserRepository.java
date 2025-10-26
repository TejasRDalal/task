package com.taskmanager.task.repositories;

import com.taskmanager.task.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByFlagTrue();

    @Modifying
    @Query("UPDATE Users t SET t.flag = false WHERE t.userId = :id")
    void deleteUser(@Param("id") Long id);

    Users findByUserId(Long id);

}
