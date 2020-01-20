package top.yoga.lol.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.common.exception.AppException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * 全局异常拦截处理逻辑
 *
 * @author luojiayu
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 业务异常
     *
     * @param ex {@link AppException}
     * @return {@link ResponseTemplate}
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(AppException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code(ex.getErrorCode())
                .msg(ex.getMessage())
                .build();
    }

    /**
     * 拦截未登录的接口信息处理
     *
     * @param ex
     * @return {@link ResponseTemplate}
     * @author luojiayu
     * @date 2020/1/7
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(AuthenticationException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code("403")
                .msg("请登录")
                .build();
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(UnauthenticatedException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code("403")
                .msg("请登录")
                .build();
    }

    /**
     * 处理参数校验异常错误
     *
     * @param ex {@link MethodArgumentNotValidException}
     * @return {@link ResponseTemplate}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code(ResponseTemplate.APP_EXCEPTION)
                .msg(bindResultErrors(ex.getBindingResult()))
                .build();
    }

    /**
     * 处理参数校验异常错误
     *
     * @param ex {@link BindException}
     * @return {@link ResponseTemplate}
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(BindException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code(ResponseTemplate.APP_EXCEPTION)
                .msg(bindResultErrors(ex.getBindingResult()))
                .build();
    }

    /**
     * 处理参数校验异常错误
     *
     * @param ex {@link ConstraintViolationException}
     * @return {@link ResponseTemplate}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation v : ex.getConstraintViolations()) {
            sb.append(v.getMessage()).append(";");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return ResponseTemplate.builder()
                .code(ResponseTemplate.APP_EXCEPTION)
                .msg(sb.toString())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseTemplate validateHandler(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code(ResponseTemplate.APP_EXCEPTION)
                .msg(ex.getMessage())
                .build();
    }

    /**
     * 处理包含BindingResult类型错误
     *
     * @return 注释的消息
     */
    private String bindResultErrors(BindingResult bindingResult) {
        if (bindingResult == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(";");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 处理http状态码异常
     *
     * @param ex       {@link ResponseStatusException}
     * @param response {@link HttpServletResponse}
     * @throws IOException io异常
     */
    @ExceptionHandler(ResponseStatusException.class)
    public void responseStatusHandler(ResponseStatusException ex, HttpServletResponse response) throws IOException {
        response.setStatus(ex.getStatus().value());
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(ex.getReason());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseTemplate exceptionHandle(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseTemplate.builder()
                .code(ResponseTemplate.UNKNOWN_EXCEPTION)
                .msg("内部错误,请联系管理员!")
                .build();
    }

}
