package by.feedblog.controller;

import by.feedblog.entity.*;
import by.feedblog.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/post")
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final CommentService commentService;
    private final UserService userService;
    private final LikeService likeService;
    private final DislikeService dislikeService;
    private final ReactionService reactionService;

    public PostController(PostService postService, CategoryService categoryService,
                          TagService tagService, CommentService commentService, UserService userService, LikeService likeService, DislikeService dislikeService, ReactionService reactionService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.userService = userService;
        this.likeService = likeService;
        this.dislikeService = dislikeService;
        this.reactionService = reactionService;
    }

    @GetMapping(path = "/create") //localhost:8080/post/create
    public String post(Model model){
        model.addAttribute("tags", tagService.getALl());
        model.addAttribute("categories", categoryService.getAll());
        return "/post/createPost";
    }

    @PostMapping(path = "/create")
    public String post(@RequestParam String title, @RequestParam int tagId,
                       @RequestParam int categoryId, @RequestParam String description, Model model, HttpSession httpSession){
        Date date = new Date();
        User user = (User) httpSession.getAttribute("user");
        if(postService.save(new Post(title,description, categoryService.getById(tagId), tagService.getById(categoryId), user, date ))){
            model.addAttribute("message", "post is create");
        } else {
            model.addAttribute("message", "post is not create");
        }
        return "/post/createPost";
    }

    @GetMapping(path = "/deletePostById")
    public String delete(Model model){
        model.addAttribute("posts", postService.getAll());
        return "redirect:/";
    }

    @PostMapping(path = "/deletePostById")
    public String delete(@RequestParam int id, Model model){
        postService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/deletePostByTitle")
    public String deleteByTitle(){
        return "/post/deletePostByTitle";
    }

    @PostMapping(path = "/deletePostByTitle")
    public String deleteByTitle(@RequestParam String title, Model model){
        if(postService.deleteByTitle(title)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "delete is not ok");
        }
        return "/post/deletePostByTitle";
    }

    @GetMapping(path = "/getAllPosts")
    public String getAll(Model model){
        model.addAttribute("posts", postService.getAll());
        return "/post/getAllPosts";
    }

    @GetMapping(path = "/getPostById")
    public String getById(){
        return "/post/getPostById";
    }

    @PostMapping(path = "/getPostById")
    public String getById(@RequestParam int id, Model model){
        if(postService.getById(id) == null){
            model.addAttribute("message", "post is not found");
        } else {
            model.addAttribute("postById", postService.getById(id));
        }
        return "/post/getPostById";
    }

    @GetMapping(path = "/getPostByTitle")
    public String getByTitle(){
        return "/post/getPostByTitle";
    }

    @PostMapping(path = "/getPostByTitle")
    public String getByTitle(@RequestParam String title, Model model){
        if(postService.getByTitle(title) == null){
            model.addAttribute("message", "post is not found");
        } else {
            model.addAttribute("postByTitle", postService.getByTitle(title));
        }
        return "/post/getPostByTitle";
    }

    @GetMapping(path = "/{id}")
    public String info(@PathVariable int id, Model model, HttpSession httpSession){
        Post byId = postService.getById(id);
        postService.setCountOfViews(byId);
        User user = (User) httpSession.getAttribute("user");
        userService.addPostToHistory(byId, user);
        model.addAttribute("comments", commentService.getAllByPost(byId));
        model.addAttribute("post", byId);
        model.addAttribute("isChecked", byId.isChecked());
        model.addAttribute("likes", likeService.getCount(byId));
        model.addAttribute("dislikes", dislikeService.getCount(byId));
        model.addAttribute("categoryName", postService.getCategoryName(byId));
        model.addAttribute("tagName", postService.getTagName(byId));
        model.addAttribute("reactions", reactionService.getALl());
        model.addAttribute("likesList", likeService.getUsers());
        model.addAttribute("dislikesList", dislikeService.getUsers());
        model.addAttribute("count", postService.getCountOfViews(byId));
        model.addAttribute("countOfReactions", reactionService.getCount(byId));
        model.addAttribute("user", byId.getUser().getUsername());
        model.addAttribute("follow", byId.getUser());
        return "/post/info";
    }

    @GetMapping(path = "/postForModer/{id}")
    public String forModer(@PathVariable int id, Model model){
        Post byId = postService.getById(id);
        model.addAttribute("post", byId);
        return "/post/postForModer";
    }

    @PostMapping(path = "/postForModer/{id}")
    public String check(@PathVariable int id, Model model){
        Post byId = postService.getById(id);
        model.addAttribute("post", byId);
        postService.addChecked(byId);
        return "redirect:/";
    }

    @GetMapping(path = "/deletePostByModer/{id}")
    public String delete(){
        return "/post/postForModer";
    }

    @PostMapping(path = "/deletePostByModer/{id}")
    public String delete(@PathVariable int id){
        postService.deleteById(id);
        return "redirect:/user/moderMenu";
    }

    @GetMapping(path = "/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("post", postService.getById(id));
        model.addAttribute("tags", tagService.getALl());
        model.addAttribute("categories", categoryService.getAll());
        return "/post/update";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@PathVariable int id, @RequestParam String description, @RequestParam int tagId, @RequestParam int categoryId){
        postService.updateDescription(id, description);
        postService.updateTag(id, tagService.getById(tagId));
        postService.updateCategory(id, categoryService.getById(categoryId));
        return "redirect:/post/{id}";
    }

    @PostMapping(path = "/like/{id}")
    public String like(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        postService.addLike(new Like(user));
        return "redirect:/post/{id}";
    }

    @PostMapping(path = "/dislike/{id}")
    public String dislike(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<Like> likes = postService.getLikes();
        Dislike dislike = new Dislike(user);
        if(likes.size() != 0){
            Like like = likes.get(likes.size() - 1);
            postService.addDislike(dislike, like);
        } else {
            postService.addDislikeWithoutRemove(dislike);
        }
        return "redirect:/post/{id}";
    }

    @GetMapping(path = "/search")
    public String search(){
        return "/post/search";
    }

    @PostMapping(path = "/search")
    public String search(@RequestParam String title, Model model){
        if(postService.getByTitle(title) == null){
            model.addAttribute("message", "post is not found");
        } else {
            model.addAttribute("post", postService.search(title));
        }
        return "/post/search";
    }

    @GetMapping(path = "/getAllByTag")
    public String getAllByTag(Model model){
        model.addAttribute("tags", tagService.getALl());
        return "/post/getAllByTag";
    }

    @PostMapping(path = "/getAllByTag")
    public String getAllByTag(@RequestParam int tagId, Model model){
        Tag byId = tagService.getById(tagId);
        if(postService.getAllByTag(byId) == null){
            model.addAttribute("message", "posts are not found");
        } else {
            model.addAttribute("posts", postService.getAllByTag(byId));
        }
        return "/post/getAllByTag";
    }

    @GetMapping(path = "/getAllByCategory")
    public String getAllBtCategory(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "/post/getAllByCategory";
    }

    @PostMapping(path = "/getAllByCategory")
    public String getAllByCategory(@RequestParam int categoryId, Model model){
        Category byId = categoryService.getById(categoryId);
        if(postService.getAllByCategory(byId) == null){
            model.addAttribute("message", "posts are not found");
        } else {
            model.addAttribute("posts", postService.getAllByCategory(byId));
        }
        return "/post/getAllByCategory";
    }

    @PostMapping(path = "/reaction")
    public String reaction(@RequestParam int reactionId, @RequestParam int postId, HttpSession httpSession){
        Post byId = postService.getById(postId);
        User user = (User) httpSession.getAttribute("user");
        Reaction reaction = reactionService.getById(reactionId);
        reactionService.reaction(reaction, byId, user);
        return "redirect:/post/{id}";
    }


    @PostMapping(path = "/addBookmark/{id}")
    public String addBookmark(@PathVariable int id, HttpSession httpSession){
        Post byId = postService.getById(id);
        User user = (User) httpSession.getAttribute("user");
        userService.addBookmark(byId, user);
        return "redirect:/post/" + id;
    }


    @PostMapping(path = "/addLike/{id}")
    public String addLike(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Post byId = postService.getById(id);
        likeService.add(new Like(user, byId));
        return "redirect:/post/{id}";
    }

    @PostMapping(path = "/addDislike/{id}")
    public String addDislike(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Post byId = postService.getById(id);
        dislikeService.add(new Dislike(user, byId));
        return "redirect:/post/{id}";
    }

}
