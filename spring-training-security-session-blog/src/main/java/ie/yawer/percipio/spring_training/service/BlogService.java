package ie.yawer.percipio.spring_training.service;

import ie.yawer.percipio.spring_training.model.BlogPost;
import ie.yawer.percipio.spring_training.model.BlogPostDto;
import ie.yawer.percipio.spring_training.model.BlogUser;
import ie.yawer.percipio.spring_training.repository.BlogPostRepository;
import ie.yawer.percipio.spring_training.repository.BlogUserRepository;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogUserRepository blogUserRepository;
    private final BlogPostRepository blogPostRepository;

    public BlogService(BlogUserRepository blogUserRepository, BlogPostRepository blogPostRepository) {
        this.blogUserRepository = blogUserRepository;
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getUserPosts(String username){
        Optional<BlogUser> blogUserOptional  = blogUserRepository.findByUsername(username);
//        if(blogUserOptional .isEmpty()) throw new UsernameNotFoundException("User " + username + " not found");
        return blogPostRepository.findByAuthorOrderByCreatedAtDesc(blogUserOptional.get());
    };

    public BlogPost saveBlogPost(BlogPostDto blogPostDto, String username){
        Optional<BlogUser> blogUserOptional = blogUserRepository.findByUsername(username);
//        if(blogUserOptional.isEmpty()) throw new UsernameNotFoundException("User " + username + " not found");
        BlogPost blogPost = new BlogPost(blogPostDto.getTitle(), blogPostDto.getContent(), blogUserOptional.get());
        return blogPostRepository.save(blogPost);
    }
}
