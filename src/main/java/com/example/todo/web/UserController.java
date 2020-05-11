package com.example.todo.web;

import com.example.todo.entity.OpenIdJson;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.utils.HttpUtil;
import com.example.todo.utils.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Value("${weixin.AppID}")
    private String appID;

    @Value("${weixin.AppSecret}")
    private String appSecret;

    @Resource
    private UserRepository userRepository;

    @GetMapping("wx_auth")
    public ResponseEntity<?> wxAuth(@RequestParam("code") String code) throws JsonProcessingException {
        String result = "";
        try {
            result = HttpUtil.doGet("https://api.weixin.qq.com/sns/jscode2session?appid="
                    + this.appID + "&secret="
                    + this.appSecret + "&js_code="
                    + code
                    + "&grant_type=authorization_code", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result, OpenIdJson.class);
        if (!userRepository.findById(openIdJson.getOpenid()).isPresent()) {
            User user = new User();
            user.setOpenId(openIdJson.getOpenid());
            userRepository.save(user);
        }
        return new ResponseEntity<>(openIdJson.getOpenid(), HttpStatus.OK);
    }

    @PutMapping("userInfo/{openid}")
    public ResponseEntity<?> userInfo(@RequestBody User user, @PathVariable("openid") String openid) {
        User old = userRepository.findById(openid).get();
        if (old != null) {
            SpringUtil.copyPropertiesIgnoreNull(user, old);
            userRepository.save(old);
        } else {
            user.setOpenId(openid);
            userRepository.save(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("userInfo/{openid}")
    public ResponseEntity<User> userInfo(@PathVariable("openid") String openid) {
        return new ResponseEntity<>(userRepository.findById(openid).get(), HttpStatus.OK);
    }

}
