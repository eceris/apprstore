package com.daou.go.appr.store.presentation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daou.go.appr.store.component.tag.TagService;
import com.daou.go.appr.store.component.tag.model.Tag;

@Controller
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
    public ResponseEntity<Void> tagSave(@RequestBody Tag tag) {

        try {
            tagService.save(tag);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> tagDelete(@RequestBody Tag tag) {

        try {
            tagService.delete(tag.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tag/list", method = RequestMethod.POST)
    public ResponseEntity<List<Tag>> selectTags(Tag tag) {

        List<Tag> lists = new ArrayList<>();

        try {
            lists = tagService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<List<Tag>>(lists, HttpStatus.CREATED);
    }

}
