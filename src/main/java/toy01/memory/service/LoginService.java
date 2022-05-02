package toy01.memory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy01.memory.domain.Member;
import toy01.memory.form.LoginForm;
import toy01.memory.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(LoginForm loginForm) {
        Member member = memberRepository.find(loginForm.getUsername(), loginForm.getPassword());
        if(member == null) return null;
        return member;
    }
}
