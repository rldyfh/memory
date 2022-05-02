package toy01.memory.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import toy01.SessionConst;
import toy01.memory.domain.Member;
import toy01.memory.domain.Posting;
import toy01.memory.form.PostingForm;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PostRepository {

    private final EntityManager em;

    /**
     * 10개씩 끊어서 불러오기
     */
    public List<Posting> findAll(int page) {
        int pageSize = 10; //한 페이지에 보여질 글의 개수

        //int postingsCount = postingsCount();

        return em.createQuery("select p from Posting p order by p.id desc", Posting.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    /**
     * 전체 글 개수
     */
    public int postingsCount() {
        return em.createQuery("select p from Posting p", Posting.class).getResultList().size();
    }

    public Posting findById(Long id) {
        return em.find(Posting.class, id);
    }


    public void changePost(Posting posting, PostingForm postingForm) {
        posting.setTitle(postingForm.getTitle());
        posting.setContent(postingForm.getContent());
        em.persist(posting);
    }


    public void savePosting(PostingForm postingForm, HttpSession httpSession) {

        Posting posting = new Posting();
        posting.setTitle(postingForm.getTitle());
        posting.setContent(postingForm.getContent());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ldt = LocalDateTime.of(now.getYear(),
                now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
        posting.setLocalDateTime(ldt);

        posting.memberPosting((Member) httpSession.getAttribute(SessionConst.LOGIN_MEMBER));

        em.persist(posting);

    }


    //가라 데이터용용
   public void posting(PostingForm postingForm) {
        Posting posting = new Posting();
        posting.setTitle(postingForm.getTitle());
        posting.setContent(postingForm.getContent());
        em.persist(posting);
    }

}
