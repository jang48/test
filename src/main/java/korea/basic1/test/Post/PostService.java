package korea.basic1.test.Post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public Post newPost(){

        Post post = new Post();
        post.setTitle("test");
        post.setContent("");
        post.setCreateDate(LocalDateTime.now());

        return  this.postRepository.save(post);
    }

    public List<Post> findAllabout(){
        return this.postRepository.findAll();
    }

     
    public Post findByID(Long id){
        Optional<Post> optionalPost =  this.postRepository.findById(id);
        if(optionalPost.isPresent()){
            return optionalPost.get();
        }

        throw new IllegalArgumentException("없는  Post 입니다.");
    }
    public void updatepost(Long id, String title, String content){
        Post post = postRepository.findById(id).get();
        if(title.trim().isEmpty()){
            title = "제목없음";
        }
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
    }

    public void deletepost(Long id){
        Post post = postRepository.findById(id).get();
        this.postRepository.delete(post);
    }

}
