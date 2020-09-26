package com.markerhub.config;

import com.markerhub.shiro.AccountRealm;
import com.markerhub.shiro.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    JwtFilter jwtFilter;


//    @Bean
//    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//
//        // inject redisSessionDAO
//        sessionManager.setSessionDAO(redisSessionDAO);
//        return sessionManager;
//    }
//
//    @Bean
//    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm,
//                                                   SessionManager sessionManager,
//                                                   RedisCacheManager redisCacheManager) {
//
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);
//
//        //inject sessionManager
//        securityManager.setSessionManager(sessionManager);
//
//        // inject redisCacheManager
//        securityManager.setCacheManager(redisCacheManager);
//        return securityManager;
//    }

    @Bean
    public DefaultWebSecurityManager getManager(AccountRealm accountRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        /*
         * 关闭shiro自带的session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }



    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/blog/save", "jwt");
        filterMap.put("/blog/delete", "jwt");
        filterMap.put("/category/saveOrUpdate", "jwt");
        filterMap.put("/category/delete", "jwt");
        filterMap.put("/tag/delete", "jwt");
        filterMap.put("/tag/saveOrUpdate", "jwt");
        filterMap.put("/user/getAllUserAndRole", "jwt");
        filterMap.put("/user/updatePassword", "jwt");
        filterMap.put("/user/updateUser", "jwt");
        filterMap.put("/comment/add", "jwt");
        filterMap.put("/comment/deleteByCid", "jwt");
        filterMap.put("/comment/getByIsRead", "jwt");
        filterMap.put("/comment/read", "jwt");
        filterMap.put("/message/save", "jwt");
        filterMap.put("/message/delete", "jwt");

        //filterMap.put("/ws/chat", "jwt");


        chainDefinition.addPathDefinitions(filterMap);
        return chainDefinition;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", jwtFilter);
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

}
