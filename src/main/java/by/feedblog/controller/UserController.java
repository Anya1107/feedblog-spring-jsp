package by.feedblog.controller;

import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import by.feedblog.service.PostService;
import by.feedblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/save")
    public String save(){
        return "/user/save";
    }

    @PostMapping(path = "/save")
    public String save(User user, Model model){
        if(userService.save(user)){
            model.addAttribute("message", "save is ok");
        } else {
            model.addAttribute("message", "user is not save");
        }
        return "/user/save";
    }

    @GetMapping(path = "/deleteById")
    public String deleteById(Model model){
        model.addAttribute("users", userService.getAll());
        return "/user/deleteById";
    }

    @PostMapping(path = "/deleteById")
    public String deleteById(@RequestParam int id,Model model){
        if(userService.deleteById(id)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "user is not delete");
        }
        return "/user/deleteById";
    }

    @GetMapping(path = "/deleteByName")
    public String deleteByName(){
        return "/user/deleteByName";
    }

    @PostMapping(path = "deleteByName")
    public String deleteByName(@RequestParam String username, Model model){
        if(userService.deleteByName(username)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "user is not delete");
        }
        return "/user/deleteByName";
    }

    @GetMapping(path = "/getAll")
    public String getAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "/user/getAll";
    }

    @GetMapping(path = "/getById")
    public String getById(){
        return "/user/getById";
    }

    @PostMapping(path = "/getById")
    public String getById(@RequestParam int id, Model model){
        if(userService.getById(id) == null){
            model.addAttribute("message", "user is not found");
        } else {
            model.addAttribute("userById", userService.getById(id));
        }
        return "/user/getById";
    }

    @GetMapping(path = "getByName")
    public String getByName(){
        return "/user/getByName";
    }

    @PostMapping(path = "/getByName")
    public String getByName(@RequestParam String username, Model model){
        if(userService.getByName(username) == null){
            model.addAttribute("message", "user is not found");
        } else {
            model.addAttribute("userByName", userService.getByName(username));
        }
        return "/user/getByName";
    }

    @GetMapping(path = "/updateName")
    public String updateName(Model model){
        model.addAttribute("users", userService.getAll());
        return "/user/updateName";
    }

    @PostMapping(path = "/updateName")
    public String updateName(@RequestParam int id, @RequestParam String name, Model model){
        User byId = userService.getById(id);
        if(byId == null){
            model.addAttribute("message", "user is not found");
        } else {
            userService.updateName(id, name);
            model.addAttribute("message", "name is update");
        }
        return "/user/updateName";
    }

    @GetMapping(path = "/updatePassword")
    public String updatePassword(Model model){
        model.addAttribute("users", userService.getAll());
        return "/user/updatePassword";
    }

    @PostMapping(path = "/updatePassword")
    public String updatePassword(@RequestParam int id, @RequestParam String password, Model model){
        User byId = userService.getById(id);
        if(byId == null){
            model.addAttribute("message", "user is not found");
        } else {
            userService.updatePassword(id,password);
            model.addAttribute("message", "password is update");
        }
        return "/user/updatePassword";
    }

    @GetMapping(path = "/updateAge")
    public String updateAge(Model model){
        model.addAttribute("users", userService.getAll());
        return "/user/updateAge";
    }

    @PostMapping(path = "/updateAge")
    public String updateAge(@RequestParam int id, @RequestParam int age, Model model){
        User byId = userService.getById(id);
        if(byId == null){
            model.addAttribute("message", "user is not found");
        } else {
            userService.updateAge(id, age);
            model.addAttribute("message", "age is update");
        }
        return "/user/updatePassword";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping(path = "/registration")
    public String registration(){
        return "/user/registration";
    }

    @PostMapping(path = "/registration")
    public String registration(User user, Model model){
        if(userService.registration(user)){
            model.addAttribute("message", "registration is ok");
        } else {
            model.addAttribute("message", "user is alr. exist");
        }
        return "/user/registration";
    }

    @GetMapping(path = "/authorization")
    public String authorization(){
        return "/user/authorization";
    }

    @PostMapping(path = "/authorization")
    public String authorization(@RequestParam String username, @RequestParam String password, HttpSession httpSession, Model model){
        if(userService.authorization(username, password)){
            User byName = userService.getByName(username);
            httpSession.setAttribute("user", byName);
            model.addAttribute("message", "authorization is ok");
        } else {
            model.addAttribute("message", "authorization is not ok");
        }
        return "/user/authorization";
    }

    @GetMapping(path = "/account")
    public String account(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("postsByUser", postService.getAllByUser(user));
        model.addAttribute("countOfFollowers", userService.countOfFollowers(user));
        return "/user/account";
    }

    @GetMapping(path = "/update")
    public String update(){
        return "/user/update";
    }

    @GetMapping(path = "/updateUserName")
    public String updateName(){
        return "/user/updateUserName";
    }

    @PostMapping(path = "/updateUserName")
    public String updateUserName(@RequestParam String newName, Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        User byId = userService.getById(user.getId());
        if(byId == null){
            model.addAttribute("message", "user is not found");
        } else {
            userService.updateName(user.getId(), newName);
            return "redirect:/user/account";
        }
        return "/user/updateUserName";
    }

    @GetMapping(path = "/updateUserPassword")
    public String updatePassword(){
        return "/user/updateUserPassword";
    }

    @PostMapping(path = "/updateUserPassword")
    public String updatePassword( @RequestParam String newPassword, Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        User byId = userService.getById(user.getId());
        if(byId == null){
            model.addAttribute("message", "user is not found");
        } else {
            userService.updatePassword(user.getId(), newPassword);
            return "redirect:/user/account";
        }
        return "/user/updateUserPassword";
    }

    @GetMapping(path = "/updateUserAge")
    public String updateAge(){
        return "/user/updateUserAge";
    }

    @PostMapping(path = "/updateUserAge")
    public String updateUserAge( @RequestParam int newAge, Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        User byId = userService.getById(user.getId());
        if(byId == null){
            model.addAttribute("message", "user is not found");
        } else {
            userService.updateAge(user.getId(), newAge);
            return "redirect:/user/account";
        }
        return "/user/updateUserAge";
    }

    @GetMapping(path = "/deleteUser")
    public String deleteUser(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        userService.deleteById(user.getId());
        return "redirect:/user/logout";
    }

    @GetMapping(path = "/moderMenu")
    public String moder(Model model){
        model.addAttribute("posts", postService.getAllUncheckedPosts());
        return "/user/moderMenu";
    }

    @GetMapping(path = "/userPosts")
    public String userPosts(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        List<Post> allByUser = postService.getAllByUser(user);
        model.addAttribute("posts", allByUser);
        return "/user/userPosts";
    }

    @GetMapping(path = "/bookmarks")
    public String bookmarks(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("bookmarks", userService.getAllBookmarks(user));
        return "/user/bookmarks";
    }

    @GetMapping(path = "/history")
    public String history(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("posts", userService.getAllHistory(user));
        return "/user/history";
    }
    
    @GetMapping(path = "/follow/{id}")
    public String follow(@PathVariable int id, Model model){
//        Post byId = postService.getById(id);
//        model.addAttribute("follow", byId.getUser());
        return "/follow/{id}";
    }

    @PostMapping(path = "/follow/{id}")
    public String follow(@PathVariable int id, HttpSession httpSession, Model model){
        Post byId = postService.getById(id);
        User follow = byId.getUser();
        User user = (User) httpSession.getAttribute("user");
        userService.follow(user, follow);
        return "redirect:/post/{id}";
    }

    @GetMapping(path = "/subscriptions")
    public String subscriptions(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("subscriptions", userService.subscriptions(user));
        return "/user/subscriptions";
    }

    @GetMapping(path = "/followers")
    public String followers(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("followers", userService.checkedFollowers(user));
        return "/user/followers";
    }

    @GetMapping(path = "/requests")
    public String requests(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("unchecked", userService.uncheckedFollowers(user));
        return "/user/requests";
    }
    
    @PostMapping(path = "/addFollower/{id}")
    public String addFollower(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        User byId = userService.getById(id);
        userService.addFollower(user, byId);
        return "redirect:/user/followers";
    }

    @PostMapping(path = "/deleteFollower/{id}")
    public String deleteFollower(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        User byId = userService.getById(id);
        userService.deleteFollower(user, byId);
        return "redirect:/user/followers";
    }

    @PostMapping(path = "/deleteSubscription/{id}")
    public String deleteSubscription(@PathVariable int id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        User byId = userService.getById(id);
        userService.deleteSubscription(user, byId);
        return "redirect:/user/followers";
    }
}
