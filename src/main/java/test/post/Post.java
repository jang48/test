package test.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import test.postbook.PostBook;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private PostBook postBook;

}
