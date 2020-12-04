/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: UserRepository
 * Author:   xutao
 * Date:     2020/11/18 10:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import spring.cloud.security.oath.common.bean.entity.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/18
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends BaseMapper<User> {

    /**
     * 通过登录用户名查询用户
     *
     * @param name
     * @return
     */
    User findByUsername(@Param("name") String name);
}