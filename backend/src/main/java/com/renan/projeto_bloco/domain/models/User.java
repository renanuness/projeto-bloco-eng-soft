package com.renan.projeto_bloco.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "app_users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;


    @ManyToMany(mappedBy = "participants")
    private Set<Tournament> tournaments;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this. password = password;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static User create(String name, String email, String password) {
        return new User(name, email, password);
    }
}
