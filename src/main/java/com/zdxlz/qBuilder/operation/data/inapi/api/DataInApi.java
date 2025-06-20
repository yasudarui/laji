package com.zdxlz.qBuilder.operation.data.inapi.api;

import com.zdxlz.qBuilder.common.core.exception.ServiceException;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.utils.StringUtils;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.redis.service.RedisService;
import com.zdxlz.qBuilder.operation.data.constant.Constants;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.dto.PublicInfoReq;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.operation.data.inapi.service.*;
import com.zdxlz.qBuilder.operation.data.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/operate/api/v1/data/in")
@Slf4j
@Api(tags = "数据接受")
public class DataInApi {


    @Autowired
    private AmgUnitService amgUnitService;

    @Autowired
    private KnwConfidentialEvaluationAgencyService knwConfidentialEvaluationAgencyService;

    @Autowired
    private KnwComExpertLibraryService knwComExpertLibraryService;

    @Autowired
    private KnwTypicalCaseLibraryService knwTypicalCaseLibraryService;

    @Autowired
    private SwitchImproveAndEvaluateService switchImproveAndEvaluateService;

    @Autowired
    private SecretImproveDeclareService improveDeclareService;

    @Autowired
    private EventNotifyInService eventNotifyInService;

    @Autowired
    private SecretEvaluateDeclareService evaluateDeclareService;


    @Autowired
    private MonCertDataService monCertDataService;

    @Autowired
    private MonRuntimeDataService monRuntimeDataService;

    @Autowired
    private MonSecretKeyDataService monSecretKeyDataService;

    @Autowired
    private MonServiceDataService monServiceDataService;

    @Autowired
    private ReportDeviceService reportDeviceService;

    @Autowired
    private ReportServiceService reportServiceService;

    @Autowired
    private ReportWarnService reportWarnService;

    @Autowired
    private SawSuperviseReportService sawSuperviseReportService;
    @Autowired
    private SecretApplicationYearTaskService secretApplicationYearTaskService;

    @Autowired
    private RedisService  redisService ;

    @Autowired
    private AmgAppService amgAppService;

    @Autowired
    private AmgSystemService amgSystemService;

    @Autowired
    private AmgDeviceService amgDeviceService;

    @Autowired
    private AmgConfigService amgConfigService;



    @Autowired
    private ProbeService probeService;

    @Autowired
    private  FrontEndProcessorService frontEndProcessorService;

    @ApiOperation("接收数据")
    @PostMapping("/accept")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "platformCode", required = true, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = Constants.TOKEN_PREFIX, required = true, paramType = "header", dataType = "String")
    })
    public String accept(@RequestBody String reqJson) {
        try {
            this.validateToken();
            CascadeReq cascadeReq = ObjectMapperUtil.objToBean(reqJson, CascadeReq.class);
            Assert.notNull(cascadeReq, "请求节点不能为空");
            return this.accept(cascadeReq);
        } catch (IllegalArgumentException | ServiceException e) {
            return ObjectMapperUtil.beanToJson(AjaxResult.error(500, e.getMessage()));
        } catch (Exception e) {
            log.error("交易请求失败:{}", String.valueOf(e));
            return ObjectMapperUtil.beanToJson(AjaxResult.error(500, "交易请求失败"));
        }
    }

    private String accept(CascadeReq cascadeReq) {
        this.validatePublicInfo(cascadeReq.getPublicInfo());
        log.info("接受到[{}]数据:{}",BizTypeEnum.getBizType(cascadeReq.getPublicInfo().getBizType()).getDesc(),ObjectMapperUtil.beanToJson(cascadeReq));
        switch (BizTypeEnum.getBizType(cascadeReq.getPublicInfo().getBizType())) {
            case UNIT_ISSUED:
                return this.amgUnitService.execTrade(cascadeReq);
            case KNW_EVALUATE_AGENCY_ISSUED:
                return knwConfidentialEvaluationAgencyService.execTrade(cascadeReq);
            case KNW_EXPERT_ISSUED:
                return knwComExpertLibraryService.execTrade(cascadeReq);
            case KNW_TYPICAL_CASE_ISSUED:
                return knwTypicalCaseLibraryService.execTrade(cascadeReq);
            case CRY_APP_DECLARE_ISSUED:
                return switchImproveAndEvaluateService.execTrade(cascadeReq);
            case CRY_IMPROVE_DECLARE_REPORT:
                return improveDeclareService.execTrade(cascadeReq);
            case EVENT_NOTICE_ISSUED:
                return eventNotifyInService.execTrade(cascadeReq);
            case CRY_EVALUATE_DECLARE_REPORT:
                return evaluateDeclareService.execTrade(cascadeReq);
            case MON_CERT_DATA_REPORT:
                return monCertDataService.execTrade(cascadeReq);
            case MON_RUNTIME_DATA_REPORT:
                return monRuntimeDataService.execTrade(cascadeReq);
            case MON_SECRET_KEY_DATA_REPORT:
                return monSecretKeyDataService.execTrade(cascadeReq);
            case MON_SERVICE_DATA_REPORT:
                return monServiceDataService.execTrade(cascadeReq);
            case SERVICE_REPORT:
                return reportServiceService.execTrade(cascadeReq);
            case DEVICE_REPORT:
                return reportDeviceService.execTrade(cascadeReq);
            case ALARM_REPORT:
                return reportWarnService.execTrade(cascadeReq);
            case SAW_SUPERVISE_REPORT:
                return sawSuperviseReportService.execTrade(cascadeReq);
            case CRY_YEAR_TASK:
                return secretApplicationYearTaskService.execTrade(cascadeReq);
            case APP_ASSET_REPORT:
                return amgAppService.execTrade(cascadeReq);
            case SYSTEM_ASSET_REPORT:
                return amgSystemService.execTrade(cascadeReq);
            case DEVICE_ASSET_REPORT:
                return amgDeviceService.execTrade(cascadeReq);
            case APP_CONFIG_REPORT:
                return amgConfigService.execTrade(cascadeReq);
            case FrontEndProcessor_REPORT:
                return frontEndProcessorService.execTrade(cascadeReq);
            case PROBE_REPORT:
                return probeService.execTrade(cascadeReq);
            default:
                throw new ServiceException("不支持的交互类型");
        }
    }

    protected void validateToken() {
//        RequestUtil.getHeaders();
        String platformCode = RequestUtil.getPlatformCode();
        if (StringUtils.isBlank(platformCode)) {
            throw new ServiceException("平台编码不能为空");
        }
        String key = String.format("%s_%s_%s",Constants.TOKEN_IN,Constants.TOKEN_PREFIX,platformCode);
        String token = RequestUtil.getToken(Constants.TOKEN_PREFIX);
        log.info("header中token:{}",token);
        if (StringUtils.isBlank(token)) {
            throw new ServiceException("访问令牌token不能为空");
        }
        String tokenValue = this.redisService.getCacheObject(key);
        log.info("平台编码:{},redis中key:{},redis中token:{}", platformCode, key, tokenValue);
        if (StringUtils.isBlank(tokenValue)) {
            throw new ServiceException("访问令牌token已过期，请重新获取");
        }
        if (!tokenValue.equals(token)){
            throw new ServiceException("访问令牌token不正确");
        }
    }

    protected void validatePublicInfo(PublicInfoReq publicInfoReq) {
        Assert.notNull(publicInfoReq, "通用节点信息异常");
        Assert.hasLength(publicInfoReq.getBizMode(), "交互模式不能为空");
        Assert.hasLength(publicInfoReq.getBizType(), "交互类型不能为空");
        Assert.hasLength(publicInfoReq.getPlatformCode(), "平台编码不能为空");
        Assert.isTrue(publicInfoReq.getPlatformCode().equals(RequestUtil.getPlatformCode()), "平台编码与header中不一致");
        Assert.hasLength(publicInfoReq.getRequestTime(), "请求时间不能为空");
    }
}
