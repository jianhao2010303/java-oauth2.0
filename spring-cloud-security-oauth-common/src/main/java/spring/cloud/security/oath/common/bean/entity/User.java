/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: User
 * Author:   xutao
 * Date:     2020/11/18 9:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oath.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.cloud.security.oath.common.base.SuperEntity;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/18
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_user")
public class User extends SuperEntity<User> {

    private String userName;

    private String password;

    private String fullName;

    private String mobile;

    @TableField(exist = false)
    private List<Role> roles;

    public User(Long id, String userName, String password, List<Role> roles) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }
}