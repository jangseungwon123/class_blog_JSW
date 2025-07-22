package com.tenco.mustache.board;

import com.tenco.mustache._core.errors.exception.Exception403;
import com.tenco.mustache._core.errors.exception.Exception404;
import com.tenco.mustache.user.SessionUser;
import com.tenco.mustache.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class BoardService {

    private final BoardJpaRepository boardJpaRepository;

    public Page<Board> findAll(Pageable pageable){

        log.info("게시글 조회 서비스 처리 시작");
        Page<Board> boardPage = boardJpaRepository.findAllJoinUser(pageable);
        log.info("게시글 목록 조회 완료 - 토탈 게시글 수 {} 개, 토탈 페이지 {}",
                boardPage.getTotalElements(), boardPage.getTotalPages());
        return boardPage;
    }


    public Board findByIdWithReplies(Long id, User sessionUser) {

        Board board = boardJpaRepository.findByIdJoinUser(id).orElseThrow(
                () -> new Exception404("게시물을 찾을 수 없습니다."));
        if(sessionUser != null) {
            boolean isBoardOwner = board.isOwner(sessionUser.getId());
            board.setBoardOwner(isBoardOwner);
        }
        return board;
    }

    // 게시글 상세 조회
    public Board findById(Long id) {
        log.info("게시글 상세 조회 서비스 시작 - ID {}", id);
        Board board = boardJpaRepository.findByIdJoinUser(id).orElseThrow(() -> {
            log.warn("게시글 조회 실패 - ID {}", id);
            return new Exception404("게시글을 찾을 수 없습니다");
        });
        log.info("게시글 상세 조회 완료 - 제목 {}", board.getTitle());
        return board;
    }

    // 게시글 소유자 확인
    public void checkBoardOwner(Long boardId, Long userId) {
        Board board = findById(boardId);
        if(!board.isOwner(userId)){
            throw new Exception403("본인 게시글만 수정할 수 있습니다.");
        }
    }

    // 게시글 수정
    public Board updateById(Long id, BoardRequest.UpdateDTO updateDTO, User sessionUser) {
        log.info("게시글 수정 서비스 시작 - 게시글 ID {}", id);
        Board board = boardJpaRepository.findById(id).orElseThrow(() -> {
            log.warn("게시글 조회 실패 - ID : {}", id);
            return new Exception404("해당 게시글이 존재하지 않습니다.");
        });
        if(!board.isOwner(sessionUser.getId())){
            throw new Exception403("본인이 작성한 게시글만 수정 가능");
        }

        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());
        log.info("게시글 수정 완료 - 게시글 ID{}, 게시글 제목 {}", id,board.getTitle());
        return board;

    }

    @Transactional
    public void deleteById(Long id, User sessionUser){
        log.info("게시글 삭제 서비스 시작 - ID  {}",id);
        Board board = boardJpaRepository.findById(id).orElseThrow(() -> {
            return new Exception404("삭제하려는 게시물 없습니다");
        });
        if (!board.isOwner(sessionUser.getId())){
            throw new Exception403("본인이 작성한 게시물만 가능합니다.");
        }
        boardJpaRepository.deleteById(id);
    }

    public Board save(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        log.info("게시글 저장 서비스 처리 시작- 제목{}, 작성자 {}",
                saveDTO.getTitle(), sessionUser.getUsername());
        Board board = saveDTO.toEntity(sessionUser);
        boardJpaRepository.save(board);
        log.info("게시글 저장 완료 - ID:{} ,제목:{}",board.getId(),board.getTitle());
        return board;
    }
}
