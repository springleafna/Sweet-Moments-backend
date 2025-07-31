package com.springleaf.sweet.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),
    
    USER_NOT_EXIST(1001, "用户不存在"),
    PASSWORD_ERROR(1002, "密码错误"),
    USER_DISABLED(1003, "用户已被禁用"),
    USER_ALREADY_EXIST(1004, "用户已存在"),
    OPERATION_FAILED(1005, "操作失败"),
    UNAUTHORIZED(1006, "未授权"),
    FORBIDDEN(1007, "禁止访问"),
    
    COURSE_NOT_EXIST(2001, "课程不存在"),
    COURSE_TIME_CONFLICT(2002, "课程时间冲突"),
    INVALID_COURSE_TYPE(2003, "无效的课程类型"),
    INVALID_TIME_RANGE(2004, "无效的时间范围"),
    
    RULE_NOT_EXIST(3001, "重复规则不存在"),
    INVALID_REPEAT_TYPE(3002, "无效的重复类型"),
    
    SETTINGS_NOT_EXIST(4001, "用户设置不存在"),
    
    PARAM_ERROR(5001, "参数错误"),
    SYSTEM_ERROR(5002, "系统错误");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
