package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginForm;
// import com.example.demo.dao.UserDao;
import com.example.demo.dto.User;
import com.example.demo.interceptor.Login;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserService.RemoveResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserRestController {
    // @Autowired
    // private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public Map<String, Object> userCreate(@RequestBody User user) {
        // 1. 유효성 검사

        // 2. 회원 가입 처리
        Map<String, Object> map = new HashMap<>();
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.join(user);

            // 3. 응답 생성
            map.put("result", "success");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("message", e.getMessage());
        }

        return map;
    }

    @PostMapping("/login")
    public Map<String, Object> userLogin(@RequestBody LoginForm loginForm) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.getUser(loginForm.getAccount());
        if (user == null) {
            map.put("result", "fail");
            map.put("message", "아이디가 없음");
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean result = passwordEncoder.matches(loginForm.getPassword(), user.getPassword());
            if (result) {
                String jwt = jwtService.createJWT(user.getAccount(), user.getEmail());
                map.put("result", "success");
                map.put("account", user.getAccount());
                map.put("jwt", jwt);

            } else {
                map.put("result", "fail");
                map.put("message", "비밀번호가 틀림");
            }

        }
        return map;

    }

    @Login
    @GetMapping("/detail")
    public Map<String, Object> userInfo(@RequestParam("account") String account) {
        Map<String, Object> resultMap = new HashMap<>();
        // service와 관련된 이름. 멤버 가져와
        User user = userService.getUser(account);
        resultMap.put("result", "success");
        resultMap.put("data", user);
        return resultMap;
    }

    @Login
    @PutMapping("/modify")
    public Map<String, Object> userModify(@RequestBody User user) {
        User dbuser = userService.modify(user); // 수정된 user 객체를 리턴받기

        Map<String, Object> map = new HashMap<>();
        if (dbuser == null) { // 수정이 되지 않아 null을 받아옴
            map.put("result", "fail"); // <String, String>
        } else {
            map.put("result", "success");
            map.put("user", dbuser); // <String, Object>
        }

        return map; // 보낸 user값이 아닌 수정된 user값 리턴
    }

    @Login
    @DeleteMapping("/remove/{account}")
    public String userRemove(@PathVariable("account") String account) {
        RemoveResult removeResult = userService.remove(account);

        JSONObject jsonObject = new JSONObject(); // {}
        if (removeResult == RemoveResult.SUCCESS) {
            jsonObject.put("result", "success");
        } else {
            jsonObject.put("result", "fail");
        }

        return jsonObject.toString(); // {"result": "success"}

    }

}