package com.taskmanager.task.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_credentials")
public class AuthCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authId;

    @OneToOne // Defines a one-to-one relationship with the User entity
    @JoinColumn(name = "userid") // Specifies the foreign key column
    private Users user; // Links AuthCredentials to a specific User


    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

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
        return "AuthCredentials{" +
                "username='" + username + '\'' +
                ", user=" + user +
                ", authId=" + authId +
                ", flag=" + flag +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
