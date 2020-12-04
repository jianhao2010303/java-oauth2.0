/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: OAuthServiceImpl
 * Author:   xutao
 * Date:     2020/11/18 10:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.cloud.security.oath.common.bean.entity.User;
import spring.cloud.security.oath.common.bean.vo.UserDetail;
import spring.cloud.security.oauth.manager.dao.UserRepository;
import spring.cloud.security.oauth.manager.service.OAuthUserDetailsService;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/18
 * @since 1.0.0
 */
@Service("OAuthUserDetailsService")
public class OAuthUserDetailsServiceImpl implements OAuthUserDetailsService {


    private final UserRepository userRepository;


    @Autowired
    public OAuthUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            //log.warn("User: {} not found", login);
            throw new UsernameNotFoundException("User " + s + " was not found in db");
            //这里找不到必须抛异常
        }
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(user, userDetail);
        return userDetail;
    }
}