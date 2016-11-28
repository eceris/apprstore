package com.daou.go.appr.store.component.document.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.daou.go.appr.store.component.category.model.Category;
import com.daou.go.appr.store.component.tag.model.Tag;

@org.springframework.data.mongodb.core.mapping.Document
public class Document {

    @Id
    private String id;

    // 양식명	
    private String name;

    // 카테고리
    @DBRef
    private Category category;

    // 태그명
    @DBRef
    private List<Tag> tags;

    // 버전
    private String version;

    // 양식본문
    private String html;

    // 양식본문(컴포넌트 포함)
    private String htmlWithComponent;

    // 양식이미지(양식본문 html image로 convert)
    private String thumbNailImage;

    // Description
    private String description;

    // 사용횟수
    private int useCount;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createdDate;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date modifiedDate;

    public Document() {
        createdDate = new Date();
        modifiedDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getThumbNailImage() {
        return thumbNailImage;
    }

    public void setThumbNailImage(String thumbNailImage) {
        this.thumbNailImage = thumbNailImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getHtmlWithComponent() {
        return htmlWithComponent;
    }

    public void setHtmlWithComponent(String htmlWithComponent) {
        this.htmlWithComponent = htmlWithComponent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

}
