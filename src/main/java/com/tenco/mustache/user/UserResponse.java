package com.tenco.mustache.user;

import lombok.Builder;
import lombok.Data;

public class UserResponse {

    @Data
    public static class JoinDTO{
        private Long id;
        private String username;
        private String email;
        private String createdAt;

        @Builder
        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt().toString();
        }
    } // end of joinDTO

    @Data
    public static class LoginDTO {
        private Long id;
        private String username;
        private String email;

        public LoginDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    } // end of LoginDTO

    @Data
    public static class UpdateDTO {
        private Long id;
        private String username;
        private String email;

        @Builder
        public UpdateDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    } // end of UpdateDTO

    @Data
    public static class DetailDTO {
        private Long id;
        private String username;
        private String email;

        @Builder
        public DetailDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    } // end of DetailDTO
}
