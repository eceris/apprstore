package com.daou.go.appr.store.component.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.go.appr.store.component.user.domain.User;
import com.daou.go.appr.store.component.user.domain.model.UserModel;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public User save(UserModel.Request user) {
        User target = new User(user.getEmail(), user.getPassword(), user.getNickname());
        return userRepository.save(target);
    }

    public User get(String id) {
        return userRepository.findOne(id);
    }

    public User update(UserModel.Request user) {
        //TODO 너무 위험하지 않나? 로직 개선 필요
        User target = modelMapper.map(user, User.class);
        return userRepository.save(target);
    }

    public void delete(String id) {
        userRepository.delete(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
