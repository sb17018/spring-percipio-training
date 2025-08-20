package ie.yawer.percipio.spring_training.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "blog_user")
@Data
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NotBlank(message = "Username in required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

}