package ie.yawer.percipio.spring_training.controller;

import ie.yawer.percipio.spring_training.model.BlogUser;
import ie.yawer.percipio.spring_training.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) { this.authService = authService; }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("user", new BlogUser());
        model.addAttribute("active", "login");
        return "auth";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new BlogUser());
        model.addAttribute("active", "register");
        return "auth";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") BlogUser blogUser, BindingResult result, Model model){
        if(result.hasErrors()) return "auth";
        if(authService.userExists(blogUser.getUsername())){
            model.addAttribute("userExistsError", "Username already exists");
            model.addAttribute("active", "login");
            return "auth";
        }
        authService.registerNewUser(blogUser);
        return "redirect:/auth/login?registered";
    }
}
