package by.feedblog.controller;

import by.feedblog.entity.Category;
import by.feedblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/save")
    public String save(){
        return "/category/saveCategory";
    }

    @PostMapping(path = "/save")
    public String save(@RequestParam String title, Model model){
        if(categoryService.save(new Category(title))){
            model.addAttribute("message", "save is ok");
        } else {
            model.addAttribute("message", "save is not ok");
        }
        return "/category/saveCategory";
    }

    @GetMapping(path = "/deleteById")
    public String delete(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "/category/deleteById";
    }

    @PostMapping(path = "/deleteById")
    public String delete(@RequestParam int id, Model model){
        if (categoryService.deleteById(id)) {
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "delete is not ok");
        }
        return "/category/deleteById";
    }

    @GetMapping(path = "/deleteByTitle")
    public String deleteByTitle(){
        return "/category/deleteByTitle";
    }

    @PostMapping(path = "/deleteByTitle")
    public String deleteByTitle(@RequestParam String title, Model model){
        if(categoryService.deleteByTitle(title)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "delete is not ok");
        }
        return "/category/deleteByTitle";
    }

    @GetMapping(path = "/getAll")
    public String getAll(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "/category/getAll";
    }

    @GetMapping(path = "/getById")
    public String getById(){
        return "/category/getById";
    }

    @PostMapping(path = "/getById")
    public String getById(@RequestParam int id, Model model){
        if(categoryService.getById(id) == null){
            model.addAttribute("message", "category is not found");
        } else {
            model.addAttribute("categoryById",categoryService.getById(id));
        }
        return "/category/getById";
    }

    @GetMapping(path = "/getByTitle")
    public String getByTitle(){
        return "/category/getByTitle";
    }

    @PostMapping(path = "/getByTitle")
    public String getByTitle(@RequestParam String title, Model model){
        if(categoryService.getByTitle(title) == null){
            model.addAttribute("message", "category is not found");
        } else {
            model.addAttribute("categoryByTitle", categoryService.getByTitle(title));
        }
        return "/category/getByTitle";
    }
}
