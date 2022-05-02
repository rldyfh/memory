package toy01.memory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toy01.SessionConst;
import toy01.memory.domain.Member;
import toy01.memory.domain.Posting;
import toy01.memory.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "1") int pageNum, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        List<Posting> findList = postService.findAll(pageNum);
        int[] pages = postService.pageList();

        if(session == null) {
            model.addAttribute("pageList", pages);
            model.addAttribute("postList", findList);
            return "home";
        }

        Member findMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(findMember == null) {
            model.addAttribute("pageList", pages);
            model.addAttribute("postList", findList);
            return "home";
        }

        model.addAttribute("pageList", pages);
        model.addAttribute("postList", findList);
        model.addAttribute("member", findMember);
        return "loginHome";
    }



}
