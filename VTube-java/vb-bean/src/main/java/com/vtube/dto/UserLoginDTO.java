package com.vtube.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer userId;
        private Integer status;
        private String email;
        private String avatarPath;
        private String phone;
        private String token;
        private String realName;
        private String userName;
        private String nickName;
        private String sex;
}
