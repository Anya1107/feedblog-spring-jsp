package by.feedblog.service;

import by.feedblog.dao.TagDao;
import by.feedblog.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagDao tagDao;

    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public boolean save(Tag tag){
        if(tagDao.containsByName(tag.getName())){
            return false;
        } else {
            tagDao.save(tag);
        }
        return true;
    }

    public boolean deleteById(int id){
        if(tagDao.containsById(id)){
            tagDao.delete(id);
            return true;
        }
        return false;
    }

    public boolean deleteByName(String name){
        if(tagDao.containsByName(name)){
            tagDao.delete(name);
            return true;
        }
        return false;
    }

    public List<Tag> getALl(){
        return tagDao.getAll();
    }

    public Tag getById(int id){
        if(tagDao.containsById(id)){
            return tagDao.getById(id);
        }
        return null;
    }

    public Tag getByName(String name){
        if(tagDao.containsByName(name)){
            return tagDao.getByName(name);
        }
        return null;
    }
}
