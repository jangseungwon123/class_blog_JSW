package com.tenco.mustache.board;

import com.tenco.mustache.user.SessionUser;
import lombok.Builder;
import lombok.Data;

public class BoardResponse {

    @Data
    public static class BoardListDTO{
        private Long id;
        private String title;
        private String content;
        private String writerName;
        private String createdAt;

        public BoardListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writerName = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt().toString();
        }
    } // end of BoardListDTO

    @Data
    public static class DetailDTO{
        private Long id;
        private String title;
        private String content;
        private String writerName;
        private String createdAt;
        // TODO - 추후 시간이 있다면 댓글도 사용하자

        public DetailDTO(Board board, SessionUser sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writerName = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt().toString();
        }
    } // end of  DetailDTO

    @Data
    public static class SaveDTO{
        private Long id;
        private String title;
        private String content;
        private String writerName;
        private String createdAt;

        @Builder
        public SaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writerName = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt().toString();
        }
    } // end of BoardListDTO

    @Data
    public static class UpdateDTO{
        private Long id;
        private String title;
        private String content;
        private String writerName;
        private String createdAt;

        @Builder
        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writerName = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt().toString();
        }
    } // end of BoardListDTO
}
