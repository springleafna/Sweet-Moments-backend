package com.springleaf.sweet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionTypeEnum {
    CANCEL("cancel", "取消"),
    RESCHEDULE("reschedule", "改期");

    private final String code;
    private final String desc;

    public static ExceptionTypeEnum getByCode(String code) {
        for (ExceptionTypeEnum exceptionType : values()) {
            if (exceptionType.getCode().equals(code)) {
                return exceptionType;
            }
        }
        return null;
    }
}