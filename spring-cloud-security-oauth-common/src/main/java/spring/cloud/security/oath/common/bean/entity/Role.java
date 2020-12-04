/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: Role
 * Author:   xutao
 * Date:     2020/11/10 16:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oath.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.cloud.security.oath.common.base.SuperEntity;

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
@AllArgsConstructor
@Builder
@TableName("sys_role")
public class Role extends SuperEntity<Role> {

    private Long id;

    private String roleName;

    private String roleCode;

    private String description;

    private String status;
}