package com.wu.sbdemo.shiro.config;

import com.wu.sbdemo.shiro.constant.UserStatus;
import com.wu.sbdemo.shiro.po.Resource;
import com.wu.sbdemo.shiro.po.Role;
import com.wu.sbdemo.shiro.po.User;
import com.wu.sbdemo.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
public class UserRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("授权");

        String username = (String) principalCollection.getPrimaryPrincipal();

        User user = userService.findByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for(Role role:user.getRoleList()){
            authorizationInfo.addRole(role.getCode());
            for(Resource r:role.getResourceList()){
                authorizationInfo.addStringPermission(r.getCode());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        log.info("登录认证");

        String username = (String) token.getPrincipal();

        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException(); // 没找到帐号
        }

        if(UserStatus.LOCKED.value.equals(user.getStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()), // username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
