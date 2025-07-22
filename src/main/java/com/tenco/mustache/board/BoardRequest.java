package com.tenco.mustache.board;

import com.tenco.mustache.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        @NotEmpty(message = "제목을 입력하세요")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이내로 작성하셔야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하세요")
        @Size(min = 1, max = 5000, message = "제목은 1자 이상 5000자 이내로 작성하셔야 합니다.")
        private String content;

        public Board toEntity(User user){
            return Board.builder()
                    .title(this.title)
                    .user(user)
                    .content(this.content)
                    .build();
        }

        public void validate() {
            if(title == null || title.trim().isEmpty()){
                throw new IllegalArgumentException("제목을 입력해주세요");
            }
            if(content == null || content.trim().isEmpty()){
                throw new IllegalArgumentException("내용을 입력해주세요");
            }
        }
    } // end of SaveDTO

    @Data
    public static class UpdateDTO{
        @NotEmpty(message = "제목을 입력하세요")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이내로 작성하셔야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하세요")
        @Size(min = 1, max = 5000, message = "제목은 1자 이상 5000자 이내로 작성하셔야 합니다.")
        private String content;
    }
}
