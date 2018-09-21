package com.hcyq.jblog.user.controller;

import com.hcyq.jblog.Utils.MD5Utils;
import com.hcyq.jblog.Utils.ResultUtils;
import com.hcyq.jblog.Utils.StringUtil;
import com.hcyq.jblog.Utils.TokenUUIDUtil;
import com.hcyq.jblog.user.bean.UserInfo;
import com.hcyq.jblog.user.dao.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    private final UserInfoMapper mapper;

    @Autowired
    public UserInfoController(UserInfoMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping(value = "/user/login")
    private String login(@RequestParam Map<String, String> map) {
        try {
            String userName = map.get("userName");
            String password = map.get("password");
            if (StringUtil.isEmpty(userName) && StringUtil.isEmpty(password)) {
                return ResultUtils.getErrorResult("参数异常");
            }
            UserInfo user = mapper.getUserByUserName(userName);
            if (user == null) {
                user = mapper.getUserByEmail(userName);
                if (user == null) {
                    return ResultUtils.getErrorResult("用户不存在");
                }
            }
            if (password.equals(user.getPassword())) {
                return ResultUtils.getSuccessResult(user);
            } else {
                return ResultUtils.getErrorResult("密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error", e);
            return ResultUtils.getErrorResult("服务器异常");
        }
    }

    @PostMapping(value = "/user/register")
    private String register(@RequestParam Map<String, String> map) {
        try {
            String userName = map.get("userName");
            String email = map.get("email");
            String password = map.get("password");
            if (StringUtil.isEmpty(userName) && StringUtil.isEmpty(password)&& StringUtil.isEmpty(email)) {
                return ResultUtils.getErrorResult("参数异常");
            }
            UserInfo temp = mapper.getUserByUserName(userName);
            if (temp != null) {
                return ResultUtils.getErrorResult("用户名重复");
            }
            temp = mapper.getUserByEmail(email);
            if (temp != null) {
                return ResultUtils.getErrorResult("邮箱已被注册");
            }
            UserInfo user = new UserInfo();
            user.setEmail(email);
            user.setUserName(userName);
            user.setPassword(MD5Utils.MD5Encode(password));
            user.setToken(TokenUUIDUtil.getUUID());
            int count = mapper.addUser(user);
            if (count <= 0) {
                return ResultUtils.getErrorResult("注册失败，数据库异常");
            }
            user = mapper.getUserByUserName(userName);
            return ResultUtils.getSuccessResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error", e);
            return ResultUtils.getErrorResult("服务器异常");
        }
    }
}
