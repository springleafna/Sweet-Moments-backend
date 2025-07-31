package com.springleaf.sweet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseTypeEnum {
    READING("reading", "阅读课"),
    VOCABULARY("vocabulary", "单词课"),
    GRAMMAR("grammar", "语法课"),
    REVIEW("review", "抗遗忘");

    private final String code;
    private final String desc;

    public static CourseTypeEnum getByCode(String code) {
        for (CourseTypeEnum courseType : values()) {
            if (courseType.getCode().equals(code)) {
                return courseType;
            }
        }
        return null;
    }
}