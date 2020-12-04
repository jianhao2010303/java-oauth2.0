/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: OrderController
 * Author:   xutao
 * Date:     2020/11/17 15:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.resource.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/11/17
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/r")
public class OrderController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyRole('ADMIN')") // 拥有p1权限方可发个文
    public String r1() {
        return "访问资源1";
    }

    @GetMapping(value = "/r2")
    @PreAuthorize("hasAnyAuthority('p2')") // 拥有p2权限方可发个文
    public String r2() {
        return "访问资源2";
    }
}