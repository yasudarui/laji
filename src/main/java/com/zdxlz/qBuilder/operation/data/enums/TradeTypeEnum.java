package com.zdxlz.qBuilder.operation.data.enums;

import lombok.Getter;

@Getter
public enum TradeTypeEnum {
    AUTO("01", "自动"),
    MANUAL("02", "手动");

    private String code;
    private String desc;

    TradeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
