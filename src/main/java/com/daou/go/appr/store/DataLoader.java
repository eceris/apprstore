package com.daou.go.appr.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.daou.go.appr.store.component.user.UserRepository;
import com.daou.go.appr.store.component.user.domain.User;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        userRepository.save(new User("lala", "lala", "lala"));
    }
}
