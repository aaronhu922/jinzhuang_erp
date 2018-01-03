package com.jinzhuang.dao;

import java.util.List;
import java.util.Map;

import com.jinzhuang.model.User;

public interface UserDao {
   public long save(User user);
   List<User> list();
   public User login(User user);

   /**
    * �����û��б�
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
    * ʵ���޸�
    *
    * @param user
    * @return
    */
   public long updateUser(User user);

   /**
    * ����û�
    *
    * @param user
    * @return
    */
   public int addUser(User user);

   /**
    * ɾ���û�
    *
    * @param id
    * @return
    */
   public long deleteUser(Long id);
}