package com.tenco.mustache.board;

import com.tenco.mustache._core.common.PageLink;
import com.tenco.mustache._core.utils.Define;
import com.tenco.mustache.user.SessionUser;
import com.tenco.mustache.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Board> boardPage = boardService.findAll(pageable);

        List<PageLink> pageLinks = new ArrayList<>();
        for(int i = 0; i < boardPage.getTotalPages(); i++) {
            pageLinks.add(new PageLink(i, i + 1, i == boardPage.getNumber()));
        }

        Integer previousPageNumber = boardPage.hasPrevious() ? boardPage.getNumber() : null;
        Integer nextPageNumber = boardPage.hasNext() ? boardPage.getNumber() + 2 : null;

        model.addAttribute("boardPage", boardPage);
        model.addAttribute("pageLinks", pageLinks);
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(
            @PathVariable(name = "id") Long id, Model model, HttpSession session){
        User sessionUser = (User) session.getAttribute(Define.SESSION_USER);
        Board board = boardService.findByIdWithReplies(id, sessionUser);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name = "id")Long boardId,
                             HttpServletRequest request, HttpSession session){
        User sessionUser = (User) session.getAttribute(Define.SESSION_USER);
        boardService.checkBoardOwner(boardId, sessionUser.getId());
        request.setAttribute("board",boardService.findById(boardId));
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update-form")
    public String update(@PathVariable(name = "id") Long boardId,
                         BoardRequest.UpdateDTO reqDTO,
                         HttpSession session){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.updateById(boardId, reqDTO, sessionUser);

        return "redirect:/board/ " + boardId;
    }

   @GetMapping("/board/save-form")
   public String saveForm(){
       return "board/save-form";
   }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO, HttpSession session){
        reqDTO.validate();
        User sessionUser = (User)session.getAttribute(Define.SESSION_USER);
        boardService.save(reqDTO,sessionUser);
        return "redirect:/";
    }

    //@PostMapping("/board/{id}/delete")
    @PostMapping("board/{id}/delete")
    public String delete(@PathVariable(name = "id")Long id, HttpSession session){
        User sessionUser = (User) session.getAttribute(Define.SESSION_USER);
        boardService.deleteById(id,sessionUser);
        return "redirect:/";
    }
}
