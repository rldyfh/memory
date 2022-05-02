package toy01.memory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toy01.memory.domain.Member;
import toy01.memory.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signIn")
    public String add() {
        return "signIn";
    }

    @PostMapping("/signIn")
    public String addMember(@ModelAttribute Member member) {
        memberService.join(member);
        return "redirect:/";
    }





}
