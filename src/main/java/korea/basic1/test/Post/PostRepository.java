package korea.basic1.test.Post;

import korea.basic1.test.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>  {


}
