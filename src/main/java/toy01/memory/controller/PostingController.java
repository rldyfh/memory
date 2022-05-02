package toy01.memory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import toy01.SessionConst;
import toy01.memory.domain.Member;
import toy01.memory.domain.Posting;
import toy01.memory.form.PostingForm;
import toy01.memory.repository.PostRepository;
import toy01.memory.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostingController {

    private final PostService postingService;
    private final PostRepository postRepository;

    @GetMapping("/posting")
    public String postingView(Model model) {
        model.addAttribute("postingForm", new PostingForm());
        return "posting";
    }

    @PostMapping("/posting")
    public String savePosting(@Validated @ModelAttribute PostingForm postingForm, BindingResult bindingResult, HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "posting";
        }

        HttpSession session = request.getSession(false);
        postingService.post(postingForm, session);

        return "redirect:/";
    }

    @GetMapping("/postings/{postId}")
    public String posting(@PathVariable Long postId, Model model) {
        Posting posting = postingService.findOne(postId);
        model.addAttribute("posting", posting);
        return "postedOne";
    }

    @GetMapping("/postings/{postId}/edit")
    public String edit(@PathVariable Long postId, Model model) {
        Posting posting = postingService.findOne(postId);
        model.addAttribute("posting", posting);
        return "postingEdit";
    }

    @PostMapping("/postings/{postId}/edit")
    public String postingEdit(@PathVariable Long postId, @ModelAttribute PostingForm postingForm) {
        Posting posting = postingService.findOne(postId);
        postRepository.changePost(posting,postingForm);
        return "redirect:/";
    }

    @GetMapping("/postings")
    public String page(@RequestParam int page, Model model, HttpServletRequest request) {
        List<Posting> postings = postingService.findAll(page); // 페이지 번호 별 게시물
        int[] pages = postingService.pageList(); // 페이지 개수

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);



        model.addAttribute("member", member);
        model.addAttribute("postList", postings);
        model.addAttribute("pageList", pages);

        return "loginHome";
    }
}
