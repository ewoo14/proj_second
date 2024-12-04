package com.sparta.msa_exam.auth.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.Builder;
import lombok.Getter;

@RedisHash("User")
@Getter
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    @Builder
    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static User create(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

    public void update(String username, String password) {
        if (username != null) {
            this.username = username;
        }
        if (password != null) {
            this.password = password;
        }
    }
}
