package ie.yawer.percipio.spring_training.config;

//import ie.yawer.percipio.spring_training.service.CustomUserDetailsService;
//import ie.yawer.percipio.spring_training.session.CustomLogoutHandler;
import ie.yawer.percipio.spring_training.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

//    @Bean
//    public CustomUserDetailsService userDetailsService(){
//        return new CustomUserDetailsService();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/enter").permitAll()
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers("/css/auth.css").permitAll()
                .requestMatchers("/js/auth.js").permitAll()
                .anyRequest().authenticated()
        );

        httpSecurity.formLogin(login->login

                .loginPage("/auth/enter")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/blog")
                .failureUrl("/auth/enter?foo")
        );


        return httpSecurity.build();
    }

}
