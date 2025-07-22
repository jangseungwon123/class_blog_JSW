package com.tenco.mustache.user;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Long id;
    private String username;
    private String email;

    @Builder
    public SessionUser(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
