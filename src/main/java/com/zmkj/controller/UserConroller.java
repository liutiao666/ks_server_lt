package com.zmkj.controller;

import com.zmkj.entity.mysql.User;
import com.zmkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: 94715
 * @CreateDate: 2025/2/28 9:05
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserConroller {

    @Autowired
    private UserService userService;

    @GetMapping("insertBatch")
    public String insertBatch(@RequestParam int num) {
        long start = System.currentTimeMillis();
        System.out.println("start insertBatch:" + start);
        try {
            userService.saveBatch(num);
            long end = System.currentTimeMillis();
            System.out.println("end insertBatch:" + end);
            System.out.println("data num:" + num + " usetime:" + (end - start));
        }catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("search")
    public String searchAll() {
        long start = System.currentTimeMillis();
        System.out.println("start searchAll:" + start);
        try {
            long num = userService.searchAll();
            long end = System.currentTimeMillis();
            System.out.println("end searchAll:" + end);
            System.out.println("data num:" + num + " usetime:" + (end - start));
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
