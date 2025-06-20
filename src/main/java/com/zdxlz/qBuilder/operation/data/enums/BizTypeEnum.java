package com.zdxlz.qBuilder.operation.data.enums;

import lombok.Getter;

@Getter
public enum BizTypeEnum {
    DEPT_ISSUED("01","组织结构下发"),
    UNIT_ISSUED("02","单位数据下发"),
    DICT_DATA_ISSUED("03","字典数据下发"),

    EVENT_NOTICE_ISSUED("04","事件通知下发"),
    KNW_EVALUATE_AGENCY_ISSUED("05","密评机构库下发"),
    KNW_EXPERT_ISSUED("06","密码专家库下发"),
    KNW_TYPICAL_CASE_ISSUED("07","典型案例库下发"),
    CRY_APP_DECLARE_ISSUED("08","商密应用申报任务管理下发"),

    SAW_SUPERVISE_REPORT("09","态势感知数据上报"),
    MON_RUNTIME_DATA_REPORT("10","运行指标信息数据上报"),
    MON_SERVICE_DATA_REPORT("11","服务指标信息数据上报"),
    MON_CERT_DATA_REPORT("12","证书指标信息数据上报"),
    MON_SECRET_KEY_DATA_REPORT("13","证书指标信息数据上报"),
    CRY_IMPROVE_DECLARE_REPORT("14","密改申报信息数据上报"),
    CRY_EVALUATE_DECLARE_REPORT("15","密评申报信息数据上报"),
    SERVICE_REPORT("16","服务报表上报"),
    DEVICE_REPORT("17","设备报表上报"),
    ALARM_REPORT("18","告警报表上报"),
    DICT_TYPE_ISSUED("19","字典类型下发"),
    CRY_YEAR_TASK("20","商密应用年度任务数据上报"),
    APP_ASSET_REPORT("21","应用档案上报"),
    DEVICE_ASSET_REPORT("22", "设备档案上报"),
    SYSTEM_ASSET_REPORT("23","系统档案上报"),
    APP_CONFIG_REPORT("24","应用配置上报"),
    PROBE_REPORT("25","探针上报"),
    FrontEndProcessor_REPORT("26","前端处理器上报"),
    ;

    private final String code ;
    private final String desc;

    BizTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(String code ){
        for(BizTypeEnum bizType : BizTypeEnum.values()){
            if(bizType.code.equals(code)){
                return bizType.desc ;
            }
        }
        return null ;
    }

    public static BizTypeEnum getBizType(String code ){
        for(BizTypeEnum bizType : BizTypeEnum.values()){
            if(bizType.code.equals(code)){
                return bizType ;
            }
        }
        return null ;
    }
}
