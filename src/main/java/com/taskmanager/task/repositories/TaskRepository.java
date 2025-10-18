package com.taskmanager.task.repositories;

import com.taskmanager.task.pojo.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByFlagTrue();

    @Modifying
    @Query("UPDATE Task t SET t.flag = false WHERE t.taskId = :id")
    void deleteTask(@Param("id") Integer id);
}
