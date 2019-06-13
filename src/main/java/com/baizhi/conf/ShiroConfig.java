/*
package com.baizhi.conf;


import com.baizhi.realm.DataSourceRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {
    */
/**
 * 创建shiro的过滤器 并且设置拦截拦截规则
 * <p>
 * 创建安全管理器
 *
 * @return 创建Realm
 *//*

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager
                                                                    securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
//// 1.设置过滤器链 在shiro实际上就是 设置过滤规则 登录页面不过滤
// 过滤链 书写过滤规则 哪些要拦截 哪些不拦截 注意：和SpringBoot集成有时候会出问题 把过过滤规则写的详细一点

        Map map = new HashMap();
//// 设置过滤规则 需要自己改
// anon 匿名可访问 不用登录就可以访问
// authc 认证后可访问 登录后可以访问
        map.put("/login.jsp", "anon");
        map.put("/user/login","anon");

// 代表所有的都需要认证
        map.put("/**", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(map);
// 2.设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        return filterFactoryBean;
    }
    */
/**
 * 创建安全管理器
 * @return
 *//*

    @Bean
    public SecurityManager getSecurityManager(DataSourceRealm dataSourceRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new
                DefaultWebSecurityManager();
// 设置自定义的Realm
        defaultWebSecurityManager.setRealm(dataSourceRealm);
        return defaultWebSecurityManager;
    }
    */
/**
 * 创建Realm
 *//*

    @Bean
    public DataSourceRealm getDataSourceRealm(){
        return new DataSourceRealm();
    }
}*/
