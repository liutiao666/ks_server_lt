package com.zmkj.service.impl;

import com.zmkj.entity.mysql.User;
import com.zmkj.mapper.mysql.UserDao;
import com.zmkj.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @Description:
 * @Author: 94715
 * @CreateDate: 2025/2/28 9:11
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    Executor asyncServiceExecutor;

    @Override
    public void saveBatch(int userNums) {
        int batchSize = 2000;
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < userNums; i++) {
            User user = new User();
            user.setId(i);
            user.setAddress("address" + i);
            user.setPassword("pwd111");
            user.setUserName("user" + i);
            user.setCountry("china");
            user.setRole("admin");
            user.setPhone("phone" + i);
            user.setSex("male");
            user.setCreateTime("2025-02-28 10:03:02");
            user.setStatus("1");
            users.add(user);
            if (i != 0 && i % batchSize == 0) {
                userDao.batchInsert(users);
                users.clear();
            }
        }
        userDao.batchInsert(users);
    }

    @Override
    public long searchAll() {
        int batchSize = 5000;
        long count = 1000000;
        long times = count / batchSize;
        for (int i = 0; i < times; i++) {
            int finalI = i;
            asyncServiceExecutor.execute(()-> {
                List<User> list = userDao.searchBatch(batchSize, batchSize * finalI);
                System.out.println(list);
            });
        }
        return count;
    }
}
