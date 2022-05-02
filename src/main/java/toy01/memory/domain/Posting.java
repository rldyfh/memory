package toy01.memory.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Posting {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    /**
     * 연관 관계 편의 메서드
     */
    public void memberPosting(Member member) {
        this.member = member;
        //member.getPostings().add(this);
    }
}
