package dev.ohhoonim.factory.domain.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    
    @Builder
    public User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    
}
