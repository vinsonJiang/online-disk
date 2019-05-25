package io.vinson.web.svc.controller;

import io.vinson.common.encode.MD5Util;
import io.vinson.web.svc.domain.User;
import io.vinson.web.svc.service.UserManager;
import io.vinson.web.svc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserManager userManager;

    @GetMapping("/login")
    public String login(Model model) {
        return "/account/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam(value = "username", required = true) String username,
                                     @RequestParam(value = "password", required = true) String password) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //根据用户名或邮箱查询用户
        User user = userService.getUserByUsernameOrEmail(username);
        if(user == null) {
            jsonMap.put("code", 1);
            jsonMap.put("msg", "没有该用户");
            return jsonMap;
        }
        if(!user.getPassword().equals(password)) {
            jsonMap.put("code", 2);
            jsonMap.put("msg", "密码错误");
            return jsonMap;
        }

        userManager.login(user.getId());//存放session到Redis中
        jsonMap.put("code", 0);
        jsonMap.put("url", "/");
        return jsonMap;
    }

    @GetMapping("/logout")
    public String logout() {
        userManager.logout();
        return "redirect:/account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/account/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(String username, String email, String phone, String password) {
        Date now = new Date();
        User user = new User();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        if(userService.getUserByUsernameOrEmail(username) != null) {
            jsonMap.put("code", 1);
            jsonMap.put("msg", "该用户名已注册");
            return jsonMap;
        }
        user.setUsername(username);
        user.setNickname(username);
        user.setEmail(email);
        user.setLoginCount(0);
        user.setRoleId(0);
        user.setPhone(phone);
        user.setPassword(password);
        user.setPasswordMd5(MD5Util.md5Hex(password));
        user.setState(0);
        user.setOnlineStatus(0);
        user.setTotalMemory(1000000L);
        user.setUsedMemory(0);
        user.setLastLoginIp("");
        user.setLastLoginTime(now);
        user.setCreateTime(now);
        user.setLastUpdateTime(now);
        userService.addUser(user);

        jsonMap.put("code", 0);
        jsonMap.put("url", "/account/login");
        return jsonMap;
    }

    @GetMapping("/person")
    public String person() {
        return "/account/person";
    }

    @PostMapping("/person")
    @ResponseBody
    public Map<String, Object> person(HttpRequest request) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("status", 0);
        return jsonMap;
    }

}
