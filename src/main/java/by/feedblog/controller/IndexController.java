package by.feedblog.controller;

import by.feedblog.entity.User;
import by.feedblog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
@RequestMapping(path = "/")
public class IndexController {
    private final PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ModelAndView getIndexPage(ModelAndView modelAndView, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user != null && postService.getAllByFollowUsers(user).size() != 0){
                modelAndView.addObject("posts", postService.getAllByFollowUsers(user));
        } else {
            modelAndView.addObject("posts", postService.getAllCheckedPosts());
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
