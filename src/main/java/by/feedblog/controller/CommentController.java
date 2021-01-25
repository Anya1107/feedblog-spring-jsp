package by.feedblog.controller;

import by.feedblog.entity.Comment;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import by.feedblog.service.CommentService;
import by.feedblog.service.PostService;
import by.feedblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String save(@RequestParam String comment, @RequestParam int postId, HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        Post byId = postService.getById(postId);
        Comment comment1 = new Comment(comment, user, byId);
        commentService.save(comment1);
        return "redirect:/post/" + postId;
    }

    @GetMapping(path = "/deleteById")
    public String deleteById(Model model){
        model.addAttribute("comments", commentService.getAll());
        return "/comment/deleteById";
    }

    @PostMapping(path = "/deleteById")
    public String deleteById(@RequestParam int id, Model model){
        if(commentService.deleteById(id)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "comment is not deleted");
        }
        return "/comment/deleteById";
    }

    @GetMapping(path = "/getById")
    public String getById(){
        return "/comment/getById";
    }

    @PostMapping(path = "/getById")
    public String getById(@RequestParam int id, Model model){
        if(commentService.getById(id) == null){
            model.addAttribute("message", "comment is not found");
        } else {
            model.addAttribute("comment", commentService.getById(id));
        }
        return "/comment/getById";
    }

    @GetMapping(path = "/getAll")
    public String getAll(Model model){
        model.addAttribute("comments", commentService.getAll());
        return "/comment/getAll";
    }

    @GetMapping(path = "/getAllByPost")
    public String getAllBtPost(Model model){
        model.addAttribute("posts", postService.getAll());
        return "/comment/getAllByPost";
    }

    @PostMapping(path = "/getAllByPost")
    public String getAllByPost(@RequestParam int id, Model model){
        if(commentService.getAllByPost(postService.getById(id)) == null){
            model.addAttribute("message", "comments are not found");
        } else {
            model.addAttribute("commentsByPost", commentService.getAllByPost(postService.getById(id)));
        }
        return "/comment/getAllByPost";
    }

    @GetMapping(path = "/getAllByUser")
    public String getAllByUser(Model model){
        model.addAttribute("users", userService.getAll());
        return "/comment/getAllByUser";
    }

    @PostMapping(path = "/getAllByUser")
    public String getAllByUser(@RequestParam int id, Model model){
        if(commentService.getAllByUser(userService.getById(id)) == null){
            model.addAttribute("message", "commets are not found");
        } else {
            model.addAttribute("commentsByUser", commentService.getAllByUser(userService.getById(id)));
        }
        return "/comment/getAllByUser";
    }

    @GetMapping(path = "/saveModerComment/{id}")
    public String save(@PathVariable int id, Model model){
        model.addAttribute("post", postService.getById(id));
        return "redirect:/post/" + id;
    }

    @PostMapping(path = "/saveModerComment/{id}")
    public String save(@RequestParam String comment, @PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Post byId = postService.getById(id);
        Comment comment1 = new Comment(comment, user, byId);
        byId.setModerComment(comment1);
        return "redirect:/post/" + id;
    }
}
