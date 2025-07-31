package com.springleaf.sweet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RepeatTypeEnum {
    DAILY("daily", "每日"),
    WEEKLY("weekly", "每周"),
    MONTHLY("monthly", "每月");

    private final String code;
    private final String desc;

    public static RepeatTypeEnum getByCode(String code) {
        for (RepeatTypeEnum repeatType : values()) {
            if (repeatType.getCode().equals(code)) {
                return repeatType;
            }
        }
        return null;
    }
}