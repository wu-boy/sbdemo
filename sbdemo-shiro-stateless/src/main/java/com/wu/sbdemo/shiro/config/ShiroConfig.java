package com.wu.sbdemo.shiro.config;

import com.wu.sbdemo.shiro.shiro.StatelessAccessControlFilter;
import com.wu.sbdemo.shiro.shiro.StatelessAuthorizingRealm;
import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: wusq
 * @date: 2018/11/17
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());

        factoryBean.getFilters().put("statelessAuthc", new StatelessAccessControlFilter());

        /*Map<String, Filter> filters = new HashMap<>();
        filters.put("statelessAuthc", new StatelessAccessControlFilter());
        factoryBean.setFilters(filters);*/

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        filterChainDefinitionMap.put("/security/login", "anon");
        filterChainDefinitionMap.put("/**", "statelessAuthc");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return factoryBean;

    }

    /*@Bean
    public StatelessAccessControlFilter statelessAuthcFilter(){
        StatelessAccessControlFilter filter = new StatelessAccessControlFilter();
        return filter;
    }*/

    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     */
    @Bean
    public Realm realm() {
        StatelessAuthorizingRealm realm = new StatelessAuthorizingRealm();
        return realm;
    }

    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
        StatelessDefaultSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory();
        return subjectFactory;
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());

        /*
         * 禁用使用Sessions 作为存储策略的实现，但它没有完全地禁用Sessions
         * 所以需要配合context.setSessionCreationEnabled(false);
         */
        ((DefaultSessionStorageEvaluator)((DefaultSubjectDAO)securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);


        securityManager.setRealm(realm());
        return securityManager;
    }

    /**
     * session管理器：
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 因为禁用掉了会话，所以没必要再定期过期会话了。
     * @return DefaultSessionManagerddddd
     */
    @Bean
    public DefaultSessionManager sessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;

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
