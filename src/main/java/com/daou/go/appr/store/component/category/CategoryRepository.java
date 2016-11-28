package com.daou.go.appr.store.component.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daou.go.appr.store.component.category.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
