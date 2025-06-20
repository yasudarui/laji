package com.zdxlz.qBuilder.operation.data.enums;

import lombok.Getter;

@Getter
public enum BizModeEnum {
    ISSUED("01", "下发"),
    REPORT("02", "上报");

    private String code;
    private String desc;

    BizModeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BizModeEnum getByCode(String code) {
        for (BizModeEnum value : BizModeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null ;
    }
}
