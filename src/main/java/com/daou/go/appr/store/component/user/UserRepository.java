package com.daou.go.appr.store.component.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daou.go.appr.store.component.user.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
