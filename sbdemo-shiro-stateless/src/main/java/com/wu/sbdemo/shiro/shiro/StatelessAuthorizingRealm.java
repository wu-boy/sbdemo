package com.wu.sbdemo.shiro.shiro;

import com.google.gson.Gson;
import com.wu.sbdemo.shiro.constant.UserStatus;
import com.wu.sbdemo.shiro.po.Resource;
import com.wu.sbdemo.shiro.po.Role;
import com.wu.sbdemo.shiro.po.User;
import com.wu.sbdemo.shiro.util.EncryptUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author: wusq
 * @date: 2018/11/17
 */
public class StatelessAuthorizingRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ValueOperations valueOperations;

    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("授权");
        String token = (String) principalCollection.getPrimaryPrincipal();
        String json = (String) valueOperations.get(token);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        log.info("认证");

        String token = (String) authenticationToken.getPrincipal();

        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        String json = (String) valueOperations.get(token);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        if(user == null) {
            throw new UnknownAccountException(); // 没找到帐号
        }
        if(UserStatus.LOCKED.value.equals(user.getStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现

        String userToken = EncryptUtils.md5(user.getPassword(), user.getCredentialsSalt());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token,
                userToken,
                getName() // realm name
        );
        return authenticationInfo;
    }
}
