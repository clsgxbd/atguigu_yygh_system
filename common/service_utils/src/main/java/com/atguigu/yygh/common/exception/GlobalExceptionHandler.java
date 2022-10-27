package com.atguigu.yygh.common.exception;

import com.atguigu.yygh.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author damu
 * @date 2022/10/19 0:41
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    public R globalExceptionHandle(Exception e) {
        e.printStackTrace();
        return R.error().message("执行全局异常处理");
    }

    // 特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    public R globalExceptionHandle(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行算数异常处理");
    }


    // 自定义异常处理
    @ExceptionHandler(YyghException.class)
    public R globalExceptionHandle(YyghException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message(e.getMsg());
    }


}
