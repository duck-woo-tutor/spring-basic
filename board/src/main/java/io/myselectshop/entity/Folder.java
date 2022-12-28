package io.myselectshop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Folder {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Folder() {
    }

    public Folder(String folderName, User user) {
        this.name = folderName;
        this.user = user;
    }
}
