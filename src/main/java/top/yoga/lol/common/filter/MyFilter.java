package top.yoga.lol.common.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.user.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器处理authc
 *
 * @author luojiayu
 * @date 2020/1/8 9:21
 */
@Slf4j
public class MyFilter extends AuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ResponseTemplate responseTemplate = new ResponseTemplate();
        log.info("进入自定义的拦截器。。。");
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        if (null == user) {
            responseTemplate.setMsg("请登录");
            responseTemplate.setCode("403");
            responseTemplate.setSuccess(false);
            responseTemplate.setData("null");
        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSON(responseTemplate).toString());
        return false;
    }
}
