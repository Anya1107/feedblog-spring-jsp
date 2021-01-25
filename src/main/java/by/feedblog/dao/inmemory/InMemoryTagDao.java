package by.feedblog.dao.inmemory;

import by.feedblog.dao.TagDao;
import by.feedblog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTagDao implements TagDao {
    private List<Tag> tags = new ArrayList<>();

    @Override
    public void save(Tag tag) {
        tags.add(tag);
    }

    @Override
    public void delete(int id) {
        for (Tag tag : tags) {
            if(tag.getId()==id){
                tags.remove(tag);
                break;
            }
        }
    }

    @Override
    public void delete(String name) {
        for (Tag tag : tags) {
            if(tag.getName().equals(name)){
                tags.remove(tag);
                break;
            }
        }
    }

    @Override
    public List<Tag> getAll() {
        return tags;
    }

    @Override
    public Tag getById(int id) {
        for (Tag tag : tags) {
            if(tag.getId()==id){
                return tag;
            }
        }
        return null;
    }

    @Override
    public Tag getByName(String name) {
        for (Tag tag : tags) {
            if(tag.getName().equals(name)){
                return tag;
            }
        }
        return null;
    }

    @Override
    public boolean containsById(int id) {
        for (Tag tag : tags) {
            if(tag.getId()==id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByName(String name) {
        for (Tag tag : tags) {
            if(tag.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
