package com.vtube.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String email;
    private String avatarPath;
    private String phone;
    private String token;
    private String realName;
    private String userName;
}
