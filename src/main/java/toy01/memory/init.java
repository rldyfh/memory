package toy01.memory;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy01.SessionConst;
import toy01.memory.domain.Member;
import toy01.memory.domain.Posting;
import toy01.memory.form.PostingForm;
import toy01.memory.repository.MemberRepository;
import toy01.memory.repository.PostRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class init {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setUsername("test");
        member.setPassword("1234");
        memberRepository.save(member);


        for(int i = 0 ; i < 105 ; i++) {
            PostingForm postingForm = new PostingForm();
            postingForm.setTitle("title"+ i);
            postingForm.setContent("content" + i);

            postRepository.posting(postingForm);
        }
    }



}
