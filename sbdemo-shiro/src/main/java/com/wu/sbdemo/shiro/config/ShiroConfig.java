package com.wu.sbdemo.shiro.config;

import com.wu.sbdemo.shiro.util.EncryptUtils;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * @author: wusq
 * @date: 2018/11/16
 */

@Configuration
public class ShiroConfig {

    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     */
    @Bean
    public Realm realm() {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {

        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();

        // Swagger匿名访问
        chain.addPathDefinition("/swagger-ui.html", "anon");
        chain.addPathDefinition("/swagger-resources", "anon");
        chain.addPathDefinition("/v2/api-docs", "anon");
        chain.addPathDefinition("/webjars/springfox-swagger-ui/**", "anon");

        chain.addPathDefinition("/*/login", "anon");
        chain.addPathDefinition("/*/hello/*", "anon");


        // 需要登录
        chain.addPathDefinition("/**", "authc");

        return chain;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(EncryptUtils.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下，
         * 在@Controller注解的类的方法中加入@RequiresRole或@RequiresPermissions注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug。
         */
        creator.setUsePrefix(true);
        return creator;
    }
}
