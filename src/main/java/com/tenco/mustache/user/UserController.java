package com.tenco.mustache.user;

import com.tenco.mustache._core.utils.Define;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
    /**
     * @GetMapping("/join-form")
     * 회원 가입 기능 요청@PostMapping("/join")
     * 로그인 화면 요청@GetMapping("/login-form")
     * 로그인 요청 @PostMapping("/login")
     * @GetMapping("/logout")
     * @GetMapping("/user/update-form")
     * * 회원 수정 기능 요청
     */

     private final UserService userService;

     @GetMapping("/join-form")
    public String joinForm(){
         log.info("회원 가입 폼 요청");
         return "user/join-form";
     }

     @GetMapping("/login-form")
    public String loginForm(){return "user/login-form";}

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO, HttpSession session){
         loginDTO.validate();
         User user = userService.login(loginDTO);
         session.setAttribute(Define.SESSION_USER,user);
         return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
         session.invalidate();
         return "redirect:/";
    }

    @GetMapping("user/update-form")
    public String updateForm(Model model, HttpSession session){
        User sessionUser = (User)session.getAttribute(Define.SESSION_USER);
        User user = userService.findById(sessionUser.getId());
        model.addAttribute("user",user);
        return "user/update-form";
    }

    @PostMapping("user/update")
    public String update(UserRequest.UpdateDTO updateDTO, HttpSession session){
         User user = (User)session.getAttribute(Define.SESSION_USER);
         User updateUser = userService.updateById(user.getId(),updateDTO);
         session.setAttribute(Define.SESSION_USER,updateUser);
         return "redirect:/user/update-form";
    }



}
