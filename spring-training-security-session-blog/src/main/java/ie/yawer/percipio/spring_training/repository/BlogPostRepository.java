package ie.yawer.percipio.spring_training.repository;

import ie.yawer.percipio.spring_training.model.BlogPost;
import ie.yawer.percipio.spring_training.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthorOrderByCreatedAtDesc(BlogUser author);
}
