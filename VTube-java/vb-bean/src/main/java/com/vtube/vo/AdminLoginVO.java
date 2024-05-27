package com.vtube.vo;

import lombok.Data;

@Data
public class AdminLoginVO {
    private Integer userId;
    private String nickName;
    private String email;
    private String avatarPath;
    private String phone;
    private String token;
    private String sex;
    private String realName;
    private String userName;
}
