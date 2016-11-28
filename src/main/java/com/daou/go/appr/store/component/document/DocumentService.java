package com.daou.go.appr.store.component.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.go.appr.store.component.category.model.Category;
import com.daou.go.appr.store.component.document.model.Document;
import com.daou.go.appr.store.component.tag.model.Tag;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public void save(Document document) {
        documentRepository.save(document);
    }

    public Document get(String id) {
        //Document document = documentRepository.findOne(id);
        return null;
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public List<Document> findByCategory(Category category) {
        return documentRepository.findByCategory(category.getId());
    }

    public List<Document> findByTags(List<Tag> tags) {
        List<String> stringTags = new ArrayList<String>();
        for (Tag tag : tags) {
            stringTags.add(tag.getName());
        }
        return documentRepository.findByTags(stringTags);
    }

    public List<Document> findByCategoryAndTags(Category category, List<Tag> tags) {
        List<String> stringTags = new ArrayList<String>();
        for (Tag tag : tags) {
            stringTags.add(tag.getName());
        }
        return documentRepository.findByCategoryAndTags(category.getId(), stringTags);
    }

}
