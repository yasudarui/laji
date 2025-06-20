package com.zdxlz.qBuilder.operation.data.enums;

import lombok.Getter;

@Getter
public enum TradeStatusEnum {
    SUCCESS("1", "成功"),
    Fail("2", "失败");

    private String code;
    private String desc;

    TradeStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
