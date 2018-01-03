package com.jinzhuang.dao;

import java.util.List;
import java.util.Map;

import com.jinzhuang.model.User;

public interface UserDao {
   public long save(User user);
   List<User> list();
   public User login(User user);

   /**
    * 查找用户列表
    *
    * @param map
    * @return
    */
   public List<User> findUsers(Map<String, Object> map);

   /**
    * @param map
    * @return
    */
   public Long getTotalUser(Map<String, Object> map);

   /**
    * 实体修改
    *
    * @param user
    * @return
    */
   public long updateUser(User user);

   /**
    * 添加用户
    *
    * @param user
    * @return
    */
   public int addUser(User user);

   /**
    * 删除用户
    *
    * @param id
    * @return
    */
   public long deleteUser(Long id);
}