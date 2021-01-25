package by.feedblog.controller;

import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.Reaction;
import by.feedblog.service.PostService;
import by.feedblog.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reaction")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/add")
    public String add(){
        return "/reaction/add";
    }

    @PostMapping(path = "/add")
    public String add(@RequestParam String reaction){
        reactionService.add(new Reaction(reaction));
        return "redirect:/";
    }

    @GetMapping(path = "/delete")
    public String deleteById(Model model){
        model.addAttribute("reactions", reactionService.getALl());
        return "/reaction/delete";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam int id){
        reactionService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/getAll")
    public String getAll(Model model){
        model.addAttribute("reactions", reactionService.getAllForModer());
        return "/reaction/getAll";
    }

    @GetMapping(path = "/getAllByPost")
    public String getAllByPost(Model model){
        model.addAttribute("posts", postService.getAll());
        return "/reaction/getAllByPost";
    }

    @PostMapping(path = "/getAllByPost")
    public String getAllByPost(@RequestParam int postId, Model model){
        Post byId = postService.getById(postId);
        if(reactionService.getAllByPost(byId) == null){
            model.addAttribute("message", "posts are not found");
        } else {
            model.addAttribute("reactions", reactionService.getAllByPost(byId));
        }
        return "/reaction/getAllByPost";
    }
}
