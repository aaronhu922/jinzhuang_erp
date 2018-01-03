/**
 * 
 */
package com.jinzhuang.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzhuang.model.User;
import com.jinzhuang.service.UserService;
import com.jinzhuang.util.MD5Util;
import com.jinzhuang.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * @author aahu
 */
@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


   @Autowired
   private UserService userService;
   
   

   @GetMapping("/users")
   public String userForm(Locale locale, Model model) {

      model.addAttribute("user", new User());
      model.addAttribute("users", userService.list());

      return "userForm";
   }

   @PostMapping("/saveUser")
   public String saveUser(@ModelAttribute("user") @Valid User user,
         BindingResult result, Model model) {

      if (result.hasErrors()) {
         
         model.addAttribute("users", userService.list());
         return "userForm";
      }

      userService.save(user);

      return "redirect:/users";
   }
   
   /**
    * 登录
    *
    * @param user
    * @param request
    * @return
    */
   @RequestMapping("/user/login")
   public String login(User user, HttpServletRequest request) {
       try {
           String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
           user.setPassword(MD5pwd);
       } catch (Exception e) {
           user.setPassword("");
       }
       User resultUser = userService.login(user);
       logger.info("request: user/login , user: " + user.toString());
       if (resultUser == null) {
           request.setAttribute("user", user);
           request.setAttribute("errorMsg", "请认真核对账号、密码！");
           return "login";
       } else {
           HttpSession session = request.getSession();
           session.setAttribute("currentUser", resultUser);
           MDC.put("userName", user.getUserName());
           return "redirect:/main.jsp";
       }
   }


   /**
    * 修改密码
    *
    * @param user
    * @param response
    * @return
    * @throws Exception
    */
   @RequestMapping("/modifyPassword")
   public String modifyPassword(User user, HttpServletResponse response) throws Exception {
       String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
       user.setPassword(MD5pwd);
       long resultTotal = userService.updateUser(user);
       JSONObject result = new JSONObject();
       if (resultTotal > 0) {
           result.put("success", true);
       } else {
           result.put("success", false);
       }
       logger.info("request: user/modifyPassword , user: " + user.toString());
       ResponseUtil.write(response, result);
       return null;
   }

   /**
    * 退出系统
    *
    * @return
    * @throws Exception
    */
   @RequestMapping("/logout")
   public String logout(HttpSession session) throws Exception {
       session.invalidate();
       logger.info("request: user/logout");
       return "redirect:/login.jsp";
   }

   /**
    * @param page
    * @param rows
    * @param s_user
    * @param response
    * @return
    * @throws Exception
    */
   @RequestMapping("/user/list")
   @ResponseBody 
   public JSONObject list(Locale locale, Model model) {
		   
//		   @RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, User s_user, HttpServletResponse response) throws Exception {
//       Map<String, Object> map = new HashMap<String, Object>();
////       if (page != null && rows != null) {
////           PageBean pageBean = new PageBean(Integer.parseInt(page),
////                   Integer.parseInt(rows));
////           map.put("start", pageBean.getStart());
////           map.put("size", pageBean.getPageSize());
////       }
//       map.put("userName", StringUtil.formatLike(s_user.getUserName()));
//       List<User> userList = userService.findUser(map);
//       Long total = userService.getTotalUser(map);
	   List<User> userList = userService.list();
       JSONObject result = new JSONObject();
       JSONArray jsonArray = JSONArray.fromObject(userList);
       result.put("rows", jsonArray);
       result.put("total", userList.size());
       logger.info("request: user/list , map: " + jsonArray);
//       ResponseUtil.write(response, result);
       return result;
//	      model.addAttribute("total", userService.list().size());
//	      model.addAttribute("rows", JSONArray.fromObject(userService.list()));
//
//	      return "userManage";
   }

   /**
    * 添加或修改管理员
    *
    * @param response
    * @return
    * @throws Exception
    */
   @RequestMapping("/user/save")
   public String save(User user, HttpServletResponse response) throws Exception {
       long resultTotal = 0;
       String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
       user.setPassword(MD5pwd);
       logger.warn(user.toString());
       if (user.getId() == null) {
           resultTotal = userService.addUser(user);
       } else {
           resultTotal = userService.updateUser(user);
       }
       JSONObject result = new JSONObject();
       if (resultTotal > 0) {
           result.put("success", true);
       } else {
           result.put("success", false);
       }
       logger.info("request: user/save , user: " + user.toString());
       ResponseUtil.write(response, result);
       return null;
   }

   /**
    * 删除管理员
    *
    * @param ids
    * @param response
    * @return
    * @throws Exception
    */
   @RequestMapping("/user/delete")
   public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
       JSONObject result = new JSONObject();
       String[] idsStr = ids.split(",");
       for (int i = 0; i < idsStr.length; i++) {
           userService.deleteUser(Integer.parseInt(idsStr[i]));
       }
       result.put("success", true);
       logger.info("request: user/delete , ids: " + ids);
       ResponseUtil.write(response, result);
       return null;
   }
}
