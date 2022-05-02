package toy01.memory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy01.memory.domain.Posting;
import toy01.memory.form.PostingForm;
import toy01.memory.repository.PostRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void post(PostingForm postingForm, HttpSession httpSession) {
        postRepository.savePosting(postingForm, httpSession);
    }

    public List<Posting> findAll(int page) {
        return postRepository.findAll(page);
    }

    //페이지의 개수
    public int[] pageList() {
        int count = postRepository.postingsCount();
        int totalPage = count / 10;
        //int pageBundle = totalPage / 5;

        totalPage = ((count % 10) == 0) ? totalPage : totalPage + 1;
        //pageBundle = ((totalPage % 5) == 0) ? pageBundle : pageBundle + 1;

//        int[][] pages = new int[pageBundle][5];
//        for(int i = 0 ; i < pageBundle ; i++) {
//            for (int j = 0  ; j < 5 ; j++) {
//                pages[i][j] = i*5 + (j+1);
//            }
//        }

//        int[] pages = new int[totalPage];
//        for (int j = 0 ; j < totalPage ; j++) {
//            pages[j] = j + 1;
//        }

        int[] pages = new int[totalPage];
        for (int j = 0 ; j < totalPage ; j++) {
            pages[j] = j + 1;
        }


        return pages;
    }

    public Posting findOne(Long id) {
        return postRepository.findById(id);
    }



}
