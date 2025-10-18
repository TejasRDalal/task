package com.taskmanager.task.pojo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Users assignee;

    @ManyToOne
    @JoinColumn(name = "report_to_id")
    private Users reportTo;

    @Column(nullable = false)
    private boolean flag = true; // default value

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", assignee=" + assignee +
                ", reportTo=" + reportTo +
                ", flag=" + flag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
