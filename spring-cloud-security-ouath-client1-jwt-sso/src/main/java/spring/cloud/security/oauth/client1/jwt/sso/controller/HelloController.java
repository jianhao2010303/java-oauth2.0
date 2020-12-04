/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: HelloController
 * Author:   xutao
 * Date:     2020/12/1 17:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.client1.jwt.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutao
 * @create 2020/12/1
 * @since 1.0.0
 */
@RestController
@RequestMapping("/hi")
public class HelloController {

    @GetMapping
    public String hello() {
        return "hello 1";
    }
}