package com.springleaf.sweet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseStatusEnum {
    NORMAL(1, "正常"),
    CANCELLED(2, "已取消"),
    COMPLETED(3, "已完成");

    private final Integer code;
    private final String desc;

    public static CourseStatusEnum getByCode(Integer code) {
        for (CourseStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}