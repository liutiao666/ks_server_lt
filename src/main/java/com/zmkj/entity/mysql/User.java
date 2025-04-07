package com.zmkj.entity.mysql;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String status;
    private String role;
    private String address;
    private String phone;
    private String email;
    private String createTime;
    private String country;
    private String sex;
}
