package com.daou.go.appr.store.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daou.go.appr.store.component.user.UserService;
import com.daou.go.appr.store.component.user.domain.User;
import com.daou.go.appr.store.component.user.domain.model.UserModel;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<UserModel.Response> create(@RequestBody UserModel.Request model) {
        User save = userService.save(model);
        return new ResponseEntity<UserModel.Response>(modelMapper.map(save, UserModel.Response.class),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<UserModel.Response> get(@RequestBody String id) {
        //TODO 유저 조회 
        User user = userService.get(id);
        return new ResponseEntity<UserModel.Response>(modelMapper.map(user, UserModel.Response.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<UserModel.Response> update(@RequestBody UserModel.Request model) {
        //TODO 유저 수정
        User update = userService.update(model);
        return new ResponseEntity<UserModel.Response>(modelMapper.map(update, UserModel.Response.class), HttpStatus.OK);
    }

}
