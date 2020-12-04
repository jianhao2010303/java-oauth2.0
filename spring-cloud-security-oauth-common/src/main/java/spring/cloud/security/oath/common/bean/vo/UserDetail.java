/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: UserDetail
 * Author:   xutao
 * Date:     2020/11/10 16:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oath.common.bean.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import spring.cloud.security.oath.common.bean.entity.Role;
import spring.cloud.security.oath.common.bean.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class UserDetail extends User implements UserDetails {


    public UserDetail(long id, String username, List<Role> roles, String password) {
        super(id, username, password, roles);
    }

    //返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(super.getRoles())) {
            for (int i = 0, j = super.getRoles().size(); i < j; i++) {
                authorities.add(new SimpleGrantedAuthority(super.getRoles().get(i).getRoleCode()));
            }
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}