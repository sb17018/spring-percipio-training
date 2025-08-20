package ie.yawer.percipio.spring_training.controller;



import ie.yawer.percipio.spring_training.model.BlogPostDto;
import ie.yawer.percipio.spring_training.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;


@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String blogHome(Model model){
        User principal = null;
        String username = "REPLACE ME";
        // Get blog posts for the current user
        model.addAttribute("posts", Collections.emptyList());
        // Check for draft in session
        BlogPostDto draft = null;
        draft = new BlogPostDto();

        model.addAttribute("newPost", draft);
        // Add username for display
        model.addAttribute("username", username);

        return "blog";
    }

    @PostMapping("/save-draft")
    public String saveDraft(@ModelAttribute("newPost") BlogPostDto newPost){
        return "redirect:/blog?draftSaved";
    }

    @PostMapping("/publish")
    public String publishPost(@Valid @ModelAttribute("newPost") BlogPostDto newPost, BindingResult result, Model model){

        User principal = null;
        String username = "REPLACE_ME";
        if(result.hasErrors()) {
            model.addAttribute("posts", blogService.getUserPosts(username));
            return "blog";
        }

        blogService.saveBlogPost(newPost, username);
        return "redirect:/blog?published";
    }

}
