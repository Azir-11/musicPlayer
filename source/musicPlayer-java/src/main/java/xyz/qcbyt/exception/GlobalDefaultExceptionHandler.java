package xyz.qcbyt.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import xyz.qcbyt.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(Exception.class);

    //未认证异常
    //需要加一个注解在某个controller 的方法上面
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result defaultExceptionHandler(HttpServletRequest req, Exception e) {

        return Result.fail(401, e.getMessage()+" 用户未认证", null);
    }
    //Shiro 异常
    @ExceptionHandler(value = ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handler(ShiroException e) {
        return Result.fail(e.getMessage());
    }

    //@Vaild 数据校验异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result BindExceptionHandler(MethodArgumentNotValidException e)
    {
        //捕获数据校验异常
        Result result = new Result();
        result.setCode(500);

        //获取实体类定义的校验注解字段上的message作为异常信息，@NotBlank(message = "用户密码不能为空！")异常信息即为"用户密码不能为空！"
        result.setResultMsg(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return Result.fail(400,"请求方式有误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result NullPointerException(HttpMessageNotReadableException e) {
        logger.error("前端传空对象了", e);
        return Result.fail(400,"请带上你要传的对象");
    }


    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public Result StringIndexOutOfBoundsException(StringIndexOutOfBoundsException e) {
        logger.error("前端传空对象了", e);
        return Result.fail(400,"莫传空文件");
    }


//    @ExceptionHandler(DefaultHandlerExceptionResolver.class)
//    public Result DefaultHandlerExceptionResolver(DefaultHandlerExceptionResolver e) {
//        logger.error("别传控制了ovo", e);
//        return Result.fail(400,"莫传空值");
//    }
}
