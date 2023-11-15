package test.postbook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostBookRepository  extends JpaRepository<PostBook, Long> {
}
