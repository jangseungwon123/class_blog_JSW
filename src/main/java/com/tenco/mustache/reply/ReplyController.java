package com.tenco.mustache.reply;

import com.tenco.mustache._core.utils.Define;
import com.tenco.mustache.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("reply/save")
    public String save(ReplyRequest.SaveDTO saveDTO, HttpSession session){
        saveDTO.validate();
        User sessionUser = (User) session.getAttribute(Define.SESSION_USER);
        replyService.save(saveDTO, sessionUser);
        return "redirect:/board" + saveDTO.getBoardId();
    }
}
