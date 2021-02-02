package com.the.dark.side.crew.fejsbuk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private UserEntity userEntity;

    public PostEntity(String message, LocalDateTime createdAt, UserEntity userEntity) {
        this.message = message;
        this.createdAt = createdAt;
        this.userEntity = userEntity;
    }
}

