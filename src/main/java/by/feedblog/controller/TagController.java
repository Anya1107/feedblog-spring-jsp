package by.feedblog.controller;

import by.feedblog.entity.Tag;
import by.feedblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(path = "/save")
    public String save(){
        return "/tag/save";
    }

    @PostMapping(path = "/save")
    public String save(@RequestParam String name, Model model){
        if(tagService.save(new Tag(name))){
            model.addAttribute("message", "save is ok");
        } else {
            model.addAttribute("message", "tag is not save");
        }
        return "/tag/save";
    }

    @GetMapping(path = "/deleteById")
    public String deleteById(Model model){
        model.addAttribute("tags", tagService.getALl());
        return "/tag/deleteById";
    }

    @PostMapping(path = "deleteById")
    public String deleteById(@RequestParam int id,Model model){
        if(tagService.deleteById(id)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "tag is not delete");
        }
        return "/tag/deleteById";
    }

    @GetMapping(path = "deleteByName")
    public String deleteByName(){
        return "/tag/deleteByName";
    }

    @PostMapping(path = "deleteByName")
    public String deleteByName(@RequestParam String name, Model model){
        if(tagService.deleteByName(name)){
            model.addAttribute("message", "delete is ok");
        } else {
            model.addAttribute("message", "tag is not delete");
        }
        return "/tag/deleteByName";
    }

    @GetMapping(path = "/getAll")
    public String getAll(Model model){
        model.addAttribute("tags", tagService.getALl());
        return "/tag/getAll";
    }

    @GetMapping(path = "/getById")
    public String getById(){
        return "/tag/getById";
    }

    @PostMapping(path = "/getById")
    public String getById(@RequestParam int id,Model model){
        if(tagService.getById(id) == null){
            model.addAttribute("message", "tag is not found");
        } else {
            model.addAttribute("tagById", tagService.getById(id));
        }
        return "/tag/getById";
    }

    @GetMapping(path = "/getByName")
    public String getByName(){
        return "/tag/getByName";
    }

    @PostMapping(path = "/getByName")
    public String getByName(@RequestParam String name, Model model){
        if(tagService.getByName(name) == null){
            model.addAttribute("message", "tag is not found");
        } else {
            model.addAttribute("tagByName", tagService.getByName(name));
        }
        return "/tag/getByName";
    }
}
