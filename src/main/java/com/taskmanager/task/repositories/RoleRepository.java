package com.taskmanager.task.repositories;

import com.taskmanager.task.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByFlagTrue();

    @Modifying
    @Query("UPDATE Role t SET t.flag = false WHERE t.id = :id")
    void updateRole(@Param("id") Integer id);

}
