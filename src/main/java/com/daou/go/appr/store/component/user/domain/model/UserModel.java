package com.daou.go.appr.store.component.user.domain.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    // 아이디
    private String id;
    // 이메일
    private String email;
    // 방문횟수
    private Integer visits;
    // 닉네임
    private String nickname;
    // 계정활성여부
    private Boolean active;

    @Data
    public static class Request extends UserModel {
        String password;
    }

    @Data
    public static class Response extends UserModel {
        private Date createdDate;
        private Date modifiedDate;

    }
}
