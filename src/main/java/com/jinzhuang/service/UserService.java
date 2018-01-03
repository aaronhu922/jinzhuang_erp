package com.jinzhuang.service;

import java.util.List;
import java.util.Map;

import com.jinzhuang.model.User;

public interface UserService {
	   void save(User user);

	   List<User> list();
	   
	    /**
	     * @param user
	     * @return
	     */
	    public User login(User user);

	    
	    /**
	     * @param map
	     * @return
	     */
	    public List<User> findUser(Map<String, Object> map);

	    /**
	     * @param map
	     * @return
	     */
	    public Long getTotalUser(Map<String, Object> map);

	    
	    public long addUser(User user);
	    /**
	     * @param user
	     * @return
	     */
	    public long updateUser(User user);

	    /**
	     * @param id
	     * @return
	     */
	    public long deleteUser(Integer id);
	}