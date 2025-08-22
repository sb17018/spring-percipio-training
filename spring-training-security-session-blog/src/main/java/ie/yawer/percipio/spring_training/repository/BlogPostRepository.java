package ie.yawer.percipio.spring_training.repository;

import ie.yawer.percipio.spring_training.model.BlogPost;
import ie.yawer.percipio.spring_training.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthorOrderByCreatedAtDesc(BlogUser author);
}
