package com.wu.sbdemo.rest.controller;

import com.wu.sbdemo.rest.dto.LoginDto;
import com.wu.sbdemo.rest.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wusq
 * @date: 2018/11/19
 */
@Api(description = "后台管理系统登录服务")
@RestController
@RequestMapping("security/")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("登录")
    @PostMapping(value = "back-login")
    public BaseResponse login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult){

        BaseResponse result = new BaseResponse();

        // 校验参数
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            log.error("登录参数错误:{}", fieldError.getDefaultMessage());
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage(fieldError.getDefaultMessage());
            return result;
        }

        try {
            if("admin".equals(loginDto.getUsername()) && "admin".equals(loginDto.getPassword())){
                result.setData(loginDto.getUsername());
            }else{
                result.setCode(HttpStatus.UNAUTHORIZED.value());
                result.setMessage("用户名或密码错误");
            }
        }catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("登录")
    @GetMapping(value = "test/{name}")
    public BaseResponse test(@PathVariable String name){
        BaseResponse result = new BaseResponse();
        result.setMessage("message");
        result.setData("hello," + name);
        return result;
    }
}
