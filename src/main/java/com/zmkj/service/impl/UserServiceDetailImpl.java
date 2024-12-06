//package com.zmkj.service.impl;
//
//import com.zmkj.dto.LoginDetail;
//import com.zmkj.entity.mysql.User;
//import com.zmkj.mapper.mysql.UserDao;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//
//@Service
//public class UserServiceDetailImpl implements UserDetailsService {
//
//    @Autowired
//    public UserDao userDao;
//
//    @SneakyThrows
//    @Override
//    public UserDetails loadUserByUsername(String userName) {
//        List<User> users = userDao.searchUsers(userName);
//        if (CollectionUtils.isEmpty(users)) {
//            throw new UsernameNotFoundException("用户名或密码不正确");
//        }
//        User user = users.get(0);
//        return new LoginDetail(user);
//    }
//}
