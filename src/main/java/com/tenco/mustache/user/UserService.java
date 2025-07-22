package com.tenco.mustache.user;

import com.tenco.mustache._core.errors.exception.Exception400;
import com.tenco.mustache._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class UserService {

    private final UserJpaRepository userJpaRepository;


    @Transactional
    public  User join(UserRequest.JoinDTO joinDTO){
        userJpaRepository.findByUsername(joinDTO.getUsername())
                .ifPresent(user -> {
                    throw new Exception400("이미 존재하는 사용자명입니다.");
                });
        return userJpaRepository.save(joinDTO.toEntity());
    }

    public User login(UserRequest.LoginDTO loginDTO) {
        return userJpaRepository
                .findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword())
                .orElseThrow(() ->{
                    return new Exception400("아이디 또는 비밀번호가 틀렸습니다.");
                });
    }

    public User findBy(Long id){
        return userJpaRepository.findById(id).orElseThrow(() -> {
            return new Exception404("사용자를 찾을 수 없습니다.");
        });
    }

    public User updateById(Long userId, UserRequest.UpdateDTO updateDTO) {

        User user = findBy(userId);
        user.setEmail(updateDTO.getEmail());
        user.setPassword(updateDTO.getPassword());
        return user;

    }

    public User findById(Long id) {
        return userJpaRepository.findById(id).orElseThrow(() -> {
           log.warn("사용자 조회 실패 - ID {}",id);
            return new Exception404("사용자를 찾을 수 없습니다.");
        });
    }
    /**
     *  User join회원가입 처리
     *  로그인 처리User login
     *  사용자 정보 조회User findById
     *  회원정보 수정 처리User updateById
     */
}
