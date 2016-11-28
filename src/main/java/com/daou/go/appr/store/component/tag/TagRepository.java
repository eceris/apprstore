package com.daou.go.appr.store.component.tag;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daou.go.appr.store.component.tag.model.Tag;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
}
