package toy01.memory.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import toy01.memory.domain.Member;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository {

    private final EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member findById(Member member) {
        return em.find(Member.class, member.getId());
    }

    public Member find(String username, String password) {
        String query = "select m from Member m where m.username = :username and m.password = :password";
        return em.createQuery(query, Member.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst().orElse(null);
    }

}
