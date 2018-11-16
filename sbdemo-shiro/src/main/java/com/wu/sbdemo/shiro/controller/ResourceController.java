package com.wu.sbdemo.shiro.controller;

import com.wu.sbdemo.shiro.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源
 * @author: wusq
 * @date: 2018/11/16
 */
@Api(description = "资源服务")
@RestController
@RequestMapping("security/resource/")
public class ResourceController {

    @ApiOperation("资源查询")
    @GetMapping(value = "query", produces = "application/json")
    @RequiresPermissions("resource:query")
    public BaseResponse query(){
        BaseResponse result = new BaseResponse();
        try {
            result.setData("资源查询");
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
