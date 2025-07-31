package com.springleaf.sweet.common;

import com.springleaf.sweet.enums.ResultCodeEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("全局异常捕获：", e);

        String msg = e.getMessage();
        if (msg == null || msg.isEmpty()) {
            msg = "服务器内部错误";
        }

        return Result.error(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append("; ");
        }
        String message = sb.toString();
        if (message.endsWith("; ")) {
            message = message.substring(0, message.length() - 2);
        }
        log.warn("参数校验异常：{}", message);
        return Result.error(ResultCodeEnum.PARAM_ERROR.getCode(), message);
    }

    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append("; ");
        }
        String message = sb.toString();
        if (message.endsWith("; ")) {
            message = message.substring(0, message.length() - 2);
        }
        log.warn("参数绑定异常：{}", message);
        return Result.error(ResultCodeEnum.PARAM_ERROR.getCode(), message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            sb.append(violation.getMessage()).append("; ");
        }
        String message = sb.toString();
        if (message.endsWith("; ")) {
            message = message.substring(0, message.length() - 2);
        }
        log.warn("约束违反异常：{}", message);
        return Result.error(ResultCodeEnum.PARAM_ERROR.getCode(), message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("非法参数异常：{}", e.getMessage());
        return Result.error(ResultCodeEnum.PARAM_ERROR.getCode(), e.getMessage());
    }
}