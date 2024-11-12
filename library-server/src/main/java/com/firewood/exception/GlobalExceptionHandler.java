package com.firewood.exception;

import com.firewood.result.Result;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleValidException(MethodArgumentNotValidException e){
        e.printStackTrace();;
        BindingResult bindingResult = e.getBindingResult();
        return Result.error(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage():"操作异常");
    }
}
