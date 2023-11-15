package test.postbook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.post.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostBookService {
    private final PostBookRepository postBookRepository;
    public List<PostBook> findallbook(){
        return this.postBookRepository.findAll();
    }

    public PostBook addbook(List<Post> postList){
        PostBook postBook = new PostBook();
        postBook.setPostList(postList);
        postBook.setName("새노트");
        postBook.setDateTime(LocalDateTime.now());

        return  this.postBookRepository.save(postBook);
    }

    public PostBook findbyIdbook(Long id){
        Optional<PostBook> postBookOptional = this.postBookRepository.findById(id);
        if(postBookOptional.isPresent()){
            return postBookOptional.get();
        }

        throw new IllegalArgumentException("없는 Book번호입니다.");
    }
}
