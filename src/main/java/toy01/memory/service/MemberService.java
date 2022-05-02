package toy01.memory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy01.memory.domain.Member;
import toy01.memory.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(Member member) {
        memberRepository.save(member);
    }


}
