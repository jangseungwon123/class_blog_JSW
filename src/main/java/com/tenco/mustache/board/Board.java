package com.tenco.mustache.board;

import com.tenco.mustache._core.utils.MyDateUtil;
import com.tenco.mustache.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient
    private boolean isBoardOwner;

    public boolean isOwner(Long checkUserId){
        return this.user.getId().equals(checkUserId);
    }

    public String getTime(){
        return MyDateUtil.timestampFormat(createdAt);
    }


//public void Update(BoardRequest.)

}
