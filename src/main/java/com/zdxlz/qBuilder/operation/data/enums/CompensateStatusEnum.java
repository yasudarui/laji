package com.zdxlz.qBuilder.operation.data.enums;

import lombok.Getter;

@Getter
public enum CompensateStatusEnum {
    WAIT("0","待补偿"),
    SUCCESS("1","成功"),
    FAIL("2","失败"),
    NULL("3","无需补偿");

    private String code;
    private String desc;

    CompensateStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
