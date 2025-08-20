package ie.yawer.percipio.spring_training.service;

import ie.yawer.percipio.spring_training.model.BlogUser;
import ie.yawer.percipio.spring_training.repository.BlogUserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final BlogUserRepository blogUserRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(BlogUserRepository blogUserRepository, PasswordEncoder passwordEncoder) {
        this.blogUserRepository = blogUserRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public BlogUser registerNewUser(BlogUser blogUser){

        if(userExists(blogUser.getUsername())) throw new RuntimeException("User already exists");
        String encodedUserPassword = passwordEncoder.encode(blogUser.getPassword());
        blogUser.setPassword(encodedUserPassword);
        return blogUserRepository.save(blogUser);
    }

    public boolean userExists(String username){
        return blogUserRepository.findByUsername(username).isPresent();
    }
}