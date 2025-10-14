package com.taskmanager.task.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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


}
