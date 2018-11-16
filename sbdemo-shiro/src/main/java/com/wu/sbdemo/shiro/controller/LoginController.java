package com.wu.sbdemo.shiro.controller;

import com.wu.sbdemo.shiro.po.User;
import com.wu.sbdemo.shiro.service.UserService;
import com.wu.sbdemo.shiro.util.BaseResponse;
import com.wu.sbdemo.shiro.util.EncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
@Api(description = "登录服务")
@RestController
@RequestMapping("security/")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping(value = "login", produces = "application/json")
    public BaseResponse login(@RequestBody User user){
        BaseResponse result = new BaseResponse();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(upToken);
            User o = userService.findByUsername(user.getUsername());
            String token = EncryptUtils.md5(o.getPassword(), o.getCredentialsSalt());
            result.setData(token);
        }catch (UnknownAccountException e){
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("用户名或密码错误");
        }catch (IncorrectCredentialsException e){
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("用户名或密码错误");
            e.printStackTrace();
        }catch (LockedAccountException e){
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMessage("账号已锁定");
        }catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("hello测试，匿名访问")
    @GetMapping(value = "hello/{name}", produces = "application/json")
    public BaseResponse hello(@PathVariable String name){
        BaseResponse result = new BaseResponse();
        try {
            result.setData("Hello," + name);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("hello测试，需要登录")
    @GetMapping(value = "needLogin/{name}", produces = "application/json")
    public BaseResponse needLogin(@PathVariable String name){
        BaseResponse result = new BaseResponse();
        try {
            result.setData("needLogin," + name);
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
