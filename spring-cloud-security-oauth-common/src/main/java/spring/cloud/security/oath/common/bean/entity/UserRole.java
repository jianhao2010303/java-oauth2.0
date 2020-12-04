/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: UserRole
 * Author:   xutao
 * Date:     2020/11/11 8:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oath.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/11
 * @since 1.0.0
 */
@Data
@TableName("sys_user_role")
public class UserRole extends Model<UserRole> {

    private Long id;

    private Long userId;

    private Long roleId;
}