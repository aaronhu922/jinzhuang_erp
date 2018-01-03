package com.jinzhuang.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinzhuang.dao.UserDao;
import com.jinzhuang.model.User;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   public void save(User user) {
      userDao.save(user);
   }

   @Transactional(readOnly = true)
   public List<User> list() {
      return userDao.list();
   }
   
   public User login(User user) {
       return userDao.login(user);
   }

 
   public List<User> findUser(Map<String, Object> map) {
       return userDao.findUsers(map);
   }

   public long updateUser(User user) {
       //防止有人胡乱修改导致其他人无法正常登陆
       if ("admin".equals(user.getUserName())) {
           return 0;
       }
       return userDao.updateUser(user);
   }

   
   public Long getTotalUser(Map<String, Object> map) {
       return userDao.getTotalUser(map);
   }

   
   public long addUser(User user) {
       if (user.getUserName() == null || user.getPassword() == null) {
           return 0;
       }
       return userDao.save(user);
   }

   
   public long deleteUser(Integer id) {
       //防止有人胡乱修改导致其他人无法正常登陆
       if (2 == id) {
           return 0l;
       }
       return userDao.deleteUser((long)id);
   }

}
