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

import com.daou.go.appr.store.component.category.CategoryService;
import com.daou.go.appr.store.component.category.model.Category;

@Controller
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public ResponseEntity<Void> categorySave(@RequestBody Category category) {
        try {
            categoryService.save(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> categoryDelete(@RequestBody Category category) {
        try {
            categoryService.delete(category.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> selectCategories() {
        List<Category> lists = new ArrayList<>();
        try {
            lists = categoryService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<List<Category>>(lists, HttpStatus.CREATED);
    }
}
