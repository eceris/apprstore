package com.daou.go.appr.store.component.document;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.daou.go.appr.store.component.document.model.Document;

public interface DocumentRepository extends MongoRepository<Document, String> {

    @Query("{ 'category.$id' : ?0}")
    List<Document> findByCategory(String categoryId);

    @Query("{'tags.$id' : {$in: ?0}}")
    List<Document> findByTags(List<String> tags);

    @Query("{ 'category.$id' : ?0 , 'tags.$id' : {$in: ?1}}")
    List<Document> findByCategoryAndTags(String categoryId, List<String> tags);

}
