package com.daou.go.appr.store.component.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.go.appr.store.component.tag.model.Tag;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public void delete(String id) {
        tagRepository.delete(id);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
