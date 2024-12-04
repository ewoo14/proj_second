package com.sparta.msa_exam.auth.user;

public record UserDto(
        String id,
        String username,
        String password
) {
    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }
}