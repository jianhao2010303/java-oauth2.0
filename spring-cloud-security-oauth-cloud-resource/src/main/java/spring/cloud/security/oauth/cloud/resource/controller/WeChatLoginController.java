package spring.cloud.security.oauth.cloud.resource.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/system/weChat")
public class WeChatLoginController {

    @Autowired
    PasswordEncoder passwordEncoder;

    public static final String APPID = "wxbcbf267fee301ca0";
    public static final String AppSecret = "02299ed1d1fa59a0456df9c7805b6377";


    @RequestMapping("/getAccessToken")
    public Object getAccessToken(@RequestBody String request) {
        try {
//            JSONObject jsonObject = parseObject(request);
//            if (jsonObject.get("code")==null&& StringUtils.isBlank(jsonObject.get("code").toString())){
//                return ResultData.build(400,"code为null");
//            }
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            params.add("appid", APPID);
//            params.add("secret",AppSecret );
//            params.add("code",jsonObject.get("code").toString() );
//            params.add("grant_type", "authorization_code");
//
//            String respString = sendPostRequest("https://api.weixin.qq.com/sns/oauth2/access_token", params);
//            JSONObject respJSON = JSON.parseObject(respString);
//            if (!respJSON.containsKey("unionid")){
//                return ResultData.build(400,"获取unionid错误");
//            }
//
//            String access_token = respJSON.getString("access_token");
//            String openid = respJSON.getString("openid");
//            MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
//            params1.add("access_token", access_token);
//            params1.add("openid",openid );
//            String jsonString1 = sendPostRequest("https://api.weixin.qq.com/sns/userinfo", params1);
//            JSONObject jsonObject1 = parseObject(jsonString1);
//            if (!jsonObject1.containsKey("unionid")){
//                return ResultData.build(400,"获取unionid错误");
//            }
//            String unionid = jsonObject1.getString("unionid");
//            String nickname = jsonObject1.getString("nickname");
//            String headimgurl = jsonObject1.getString("headimgurl");
//            SysUser sysUser = new SysUser();
//            sysUser.setEnabled(true);
//            sysUser.setAccountNonExpired(true);
//            sysUser.setAccountNonLocked(true);
//            sysUser.setCredentialsNonExpired(true);
//            sysUser.setUsername(unionid);
//            sysUser.setNickName(nickname);
//            sysUser.setPassword(passwordEncoder.encode("123456"));
//            sysUser.setCreateDate(new Date());
//            sysUser.setUpdateDate(new Date());
//            sysUser.setUnionid(unionid);
//            SysUser sysUsers = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", unionid));
//            if (sysUsers==null){
//                boolean save = sysUserService.save(sysUser);
//                RedisUtils.set(unionid,access_token,7200L);
//                return ResultData.ok("操作成功",sysUser);
//            }
//            return ResultData.ok("操作成功",sysUsers);
        } catch (Exception e) {
            e.printStackTrace();
            //return ResultData.build(400,"转换错误");
        }
        return null;
    }


    public static String sendPostRequest(String url, MultiValueMap<String, String> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);

        return response.getBody();
    }


}
