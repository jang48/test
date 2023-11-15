package test.postbook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import test.post.Post;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class PostBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "postBook")
    private List<Post> postList;
}
