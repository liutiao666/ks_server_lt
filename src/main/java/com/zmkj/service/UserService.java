package com.zmkj.service;

import com.zmkj.entity.mysql.User;

import java.util.List;

public interface UserService {

    void saveBatch(int userNums);

    long searchAll();
}
