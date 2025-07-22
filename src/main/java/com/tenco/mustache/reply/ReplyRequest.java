package com.tenco.mustache.reply;

import com.tenco.mustache._core.errors.exception.Exception400;
import com.tenco.mustache.board.Board;
import com.tenco.mustache.user.User;
import lombok.Data;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Long boardId;
        private String comment;

        public void validate(){
            if(comment == null || comment.trim().isEmpty()){
                throw new Exception400("댓글 내용을 입력하세요");
            }
            if(comment.length() > 500){
                throw new Exception400("500자 이상 기입 불가입니다.");
            }
            if (boardId == null){
                throw new Exception400("게시글 정보가 필요합니다.");
            }
        }

        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .comment(comment.trim())
                    .user(sessionUser)
                    .board(board)
                    .build();
        }

    }


}
