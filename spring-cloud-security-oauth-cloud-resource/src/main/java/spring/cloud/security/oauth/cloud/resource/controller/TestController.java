/**
 * Copyright (C), 2015-2020, xuct.net
 * FileName: TestController
 * Author:   xutao
 * Date:     2020/11/27 15:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.cloud.security.oauth.cloud.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.security.oath.common.bean.vo.ResultJson;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author user
 * @create 2020/11/27
 * @since 1.0.0
 */
@RestController
@RequestMapping("/r")
public class TestController {

    @PreAuthorize("hasAnyRole('ADMIN')") // 拥有p1权限方可发个文
    @GetMapping("/r1")
    public ResultJson r1() {
        return ResultJson.ok();
    }
}