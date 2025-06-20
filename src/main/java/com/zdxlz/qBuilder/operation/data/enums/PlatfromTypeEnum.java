package com.zdxlz.qBuilder.operation.data.enums;

import lombok.Getter;

@Getter
public enum PlatfromTypeEnum {
    FIRST_P("1"),
    SECOND_P("2");
    private String code ;

    PlatfromTypeEnum(String code) {
        this.code = code;
    }
}
