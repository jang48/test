package test.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.postbook.PostBook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void addpost(PostBook postBook){
        Post post = new Post();
        post.setTitle("title..");
        post.setContent("");
        post.setPostBook(postBook);
        post.setCreateDate(LocalDateTime.now());

        this.postRepository.save(post);
    }

    public List<Post> findallpost(){
        return this.postRepository.findAll();
    }

    public List<Post> newpost(){
        return this.postRepository.findAll();
    }

    public Post findbyidPost(Long id){
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()){
            return optionalPost.get();
        }

        throw new IllegalArgumentException("없는 포스트번호입니다.");
    }

    public void updatepost(Long id, String title, String content){
        Post post =  findbyidPost(id);

        if(title.trim().isEmpty()){
            title = "제목없음";
        }

        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);

    }

    public void deletepost(Long id){
        Post post =  findbyidPost(id);
        this.postRepository.delete(post);
    }
}
