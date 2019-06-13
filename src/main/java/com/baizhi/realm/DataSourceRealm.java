package com.baizhi.realm;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class DataSourceRealm extends AuthenticatingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();

        User user = new User();
        user.setName(userName);
        user = userMapper.selectOne(user);

        if (user != null) {

            SimpleAccount simpleAccount = new SimpleAccount(user.getName(), user.getPassword(), this.getName());
            return simpleAccount;
        } else {
            return null;
        }

    }
}
