package ie.yawer.percipio.spring_training.service;

import ie.yawer.percipio.spring_training.model.BlogUser;
import ie.yawer.percipio.spring_training.repository.BlogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BlogUser> blogUser = blogUserRepository.findByUsername(username); // (1)
        if(blogUser.isEmpty()) {                                                   // (2)
            throw new UsernameNotFoundException("User {username} not found");      // (3)
        }
        return User                                                                // (4)
                .builder()
                .username(blogUser.get().getUsername())
                .password(blogUser.get().getPassword())
                .build();
    }
}
