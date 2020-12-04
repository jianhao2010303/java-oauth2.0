package spring.cloud.security.oauth.cloud.uua.config.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import spring.cloud.security.oath.common.bean.vo.ResultCode;
import spring.cloud.security.oath.common.bean.vo.ResultJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author ys
 * @date 2020/4/10 11:17
 */
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    //1、认证失败后响应json字符串
    ResultJson result = ResultJson.failure(ResultCode.UNAUTHORIZED, e.getMessage());
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.getOutputStream().write(result.toJsonString().getBytes("UTF-8"));

  }
}
