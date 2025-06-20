package com.zdxlz.qBuilder.operation.data.outapi.api;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.DeleteAmgAppConfigDto;
import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.SaveAmgAppConfigDto;
import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.SaveAmgAppDto;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgApp;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgDevice;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgSystem;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgUnit;
import com.zdxlz.qBuilder.common.core.domain.compliance.check.entity.FrontEndProcessor;
import com.zdxlz.qBuilder.common.core.domain.compliance.check.entity.Probe;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.*;
import com.zdxlz.qBuilder.common.core.domain.eventcenter.dto.EventNotifyDto;
import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.*;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonCertData;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonCollectSecretKey;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonRuntimeData;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonServiceData;
import com.zdxlz.qBuilder.common.core.domain.report.dto.ReportDeviceCountDto;
import com.zdxlz.qBuilder.common.core.domain.report.dto.ReportServiceCountDto;
import com.zdxlz.qBuilder.common.core.domain.report.dto.ReportWarnCountDto;
import com.zdxlz.qBuilder.common.core.domain.situation.awareness.entity.SawSuperviseReport;
import com.zdxlz.qBuilder.common.core.domain.system.entity.SysDept;
import com.zdxlz.qBuilder.common.core.domain.system.entity.SysDictData;
import com.zdxlz.qBuilder.common.core.domain.system.entity.SysDictType;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.security.annotation.InnerAuth;
import com.zdxlz.qBuilder.operation.data.outapi.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/issued")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "数据下发")
public class DataOutApi {

    private final AmgUnitIssuedService amgUnitIssuedService;
    private final SysDeptIssuedService sysDeptIssuedService;
    private final SysDictTypeIssuedService sysDictTypeIssuedService;
    private final SysDictDataIssuedService sysDictDataIssuedService;

    private final EventNotifyIssuedService eventNotifyIssuedService;

    private final SwitchImproveAndEvaluateIssuedService switchImproveAndEvaluateIssuedService;

    private final SecretImproveDeclareIssuedService improveDeclareIssuedService;

    private final SecretEvaluateDeclareIssuedService evaluateDeclareIssuedService;

    private final ReportDeviceIssuedService reportDeviceIssuedService;
    private final ReportServiceIssuedService reportServiceIssuedService;
    private final ReportWarnIssuedService reportWarnIssuedService;
    private final MonCertDataIssuedService monCertDataIssuedService;
    private final MonRuntimeDataIssuedService monRuntimeDataIssuedService;
    private final MonSecretKeyDataIssuedService monSecretKeyDataIssuedService;
    private final MonServiceDataIssuedService monServiceDataIssuedService;

    private final KnwComExpertLibraryIssuedService knwComExpertLibraryIssuedService;
    private final KnwTypicalCaseLibraryIssuedService knwTypicalCaseLibraryIssuedService;
    private final KnwConfidentialEvaluationAgencyIssuedService knwConfidentialEvaluationAgencyIssuedService;

    private final SawSuperviseReportIssueService sawSuperviseReportIssueService;

    private final SecretApplicationYearTaskReportService yearTaskIssuedService;

    private final AmgAppIssuedService amgAppIssuedService;
    private final AmgDeviceIssuedService amgDeviceIssuedService;
    private final AmgSystemIssuedService amgSystemIssuedService;
    private final AmgAppConfigIssuedService amgAppConfigIssuedService;
    private final ProbeIssuedService probeIssuedService;
    private final FrontEndProcessorIssuedService frontEndProcessorIssuedService;


    @PostMapping("/amgUnit")
    @InnerAuth
    @ApiOperation(value = "下发单位")
    public void saveAmgUnit(@RequestBody AmgUnit unit) {
        amgUnitIssuedService.saveAmgUnit(unit);
    }

    @PutMapping("/amgUnit")
    @InnerAuth
    @ApiOperation(value = "修改单位")
    public void updateAmgUnit(@RequestBody AmgUnit unit) {
        amgUnitIssuedService.updateAmgUnit(unit);
    }


    @DeleteMapping("/amgUnit")
    @InnerAuth
    @ApiOperation(value = "删除单位")
    public void removeAmgUnit(@RequestBody AmgUnit unit) {
        amgUnitIssuedService.removeAmgUnit(unit);

    }

    @PostMapping("/eventNotify")
    @InnerAuth
    @ApiOperation(value = "下发事件通知")
    public void eventNotifyIssued(@RequestBody EventNotifyDto eventNotifyDto) {
        this.eventNotifyIssuedService.issuedEventNotify(eventNotifyDto);
    }

    @PostMapping("/switchImproveAndEvaluate")
    @InnerAuth
    @ApiOperation(value = "下发开关改进和评估")
    public void issuedSwitchImproveAndEvaluate(@RequestBody SaveAppPwdIndexInfoDto saveAppPwdIndexInfoDto) {
        this.switchImproveAndEvaluateIssuedService.issuedSwitchImproveAndEvaluate(saveAppPwdIndexInfoDto);
    }

    @PostMapping("/issuedSecretImprove")
    @InnerAuth
    @ApiOperation(value = "下发密钥改进")
    public void issuedSecretImprove(@RequestBody InsertCryImproveDeclareDto insertCryImproveDeclareDto) {
        this.improveDeclareIssuedService.issuedSecretImprove(insertCryImproveDeclareDto);
    }

    @PostMapping("/issuedSecretEvaluate")
    @InnerAuth
    @ApiOperation(value = "下发密钥评估")
    public void issuedSecretEvaluate(@RequestBody InsertCryEvaluateDeclareBaseInfoDto insertCryEvaluateDeclareBaseInfoDto) {
        this.evaluateDeclareIssuedService.issuedSecretEvaluate(insertCryEvaluateDeclareBaseInfoDto);
    }

    @PostMapping("/collectRuntimeData")
    @InnerAuth
    @ApiOperation(value = "下发运行时数据")
    public void collectRuntimeData(@RequestBody MonRuntimeData runtimeData) {
        this.monRuntimeDataIssuedService.collectRuntimeData(runtimeData);
    }

    @PostMapping("/collectServiceData")
    @InnerAuth
    @ApiOperation(value = "下发服务数据")
    public void collectServiceData(@RequestBody MonServiceData serviceData) {
        this.monServiceDataIssuedService.collectServiceData(serviceData);
    }

    @PostMapping("/collectCertData")
    @InnerAuth
    @ApiOperation(value = "下发证书数据")
    public void collectCertData(@RequestBody MonCertData certData) {
        this.monCertDataIssuedService.collectCertData(certData);
    }

    @PostMapping("/collectSecretKey")
    @InnerAuth
    @ApiOperation(value = "下发密钥数据")
    public void collectSecretKey(@RequestBody MonCollectSecretKey collectSecretKey) {
        this.monSecretKeyDataIssuedService.collectSecretKey(collectSecretKey);
    }

    @PostMapping("/device/count")
    @InnerAuth
    @ApiOperation(value = "下发设备数量")
    public void reportDeviceCountOut(@RequestBody List<ReportDeviceCountDto> deviceCountList) {
        this.reportDeviceIssuedService.reportDeviceCountOut(deviceCountList);
    }

    @PostMapping("/service/count")
    @InnerAuth
    @ApiOperation(value = "下发服务数量")
    public void reportServiceCountOut(@RequestBody List<ReportServiceCountDto> serviceCountList) {
        this.reportServiceIssuedService.reportServiceCountOut(serviceCountList);
    }

    @PostMapping("/warn/count")
    @InnerAuth
    @ApiOperation(value = "下发告警数量")
    public void reportWarnCountOut(@RequestBody List<ReportWarnCountDto> warnCountList) {
        this.reportWarnIssuedService.reportWarnCountOut(warnCountList);
    }

    @PostMapping("/sawsSupervise/report")
    @InnerAuth
    @ApiOperation(value = "下发态势感知")
    public void getSawSuperviseReport(@RequestBody List<SawSuperviseReport> sawSuperviseReportList) {
        sawSuperviseReportIssueService.getSawSuperviseReport(sawSuperviseReportList);
    }


    /**
     * 保存密码专家库
     *
     * @param saveComExpertLibraryDto
     * @return
     */
    @PostMapping("/expert/library/save")
    @InnerAuth
    @ApiOperation(value = "保存密码专家库")
    public void saveKnwComExpertLibrary(@RequestBody SaveComExpertLibraryDto saveComExpertLibraryDto) {
        this.knwComExpertLibraryIssuedService.saveComExpertLibrary(saveComExpertLibraryDto);
    }

    /**
     * 删除密码专家库
     *
     * @param deleteComExpertLibraryDto
     * @return
     */
    @PostMapping("/expert/library/delete")
    @InnerAuth
    @ApiOperation(value = "删除密码专家库")
    public void deleteComExpertLibraryById(@RequestBody DeleteComExpertLibraryDto deleteComExpertLibraryDto) {
        this.knwComExpertLibraryIssuedService.deleteComExpertLibrary(deleteComExpertLibraryDto);
    }

    /**
     * 更新密码专家库
     */
    @PostMapping("/expert/library/update")
    @InnerAuth
    @ApiOperation(value = "更新密码专家库")
    public void updateComExpertLibrary(@RequestBody SaveComExpertLibraryDto updateComExpertLibrary) {
        this.knwComExpertLibraryIssuedService.updateComExpertLibrary(updateComExpertLibrary);
    }

    /**
     * 保存典型案例库
     *
     * @param saveTypicalCaseLibraryDto
     * @return
     */
    @PostMapping("/typical/case/library/save")
    @InnerAuth
    @ApiOperation(value = "保存典型案例库")
    public void saveTypicalCaseLibraryDto(@RequestBody SaveTypicalCaseLibraryDto saveTypicalCaseLibraryDto) {
        this.knwTypicalCaseLibraryIssuedService.saveTypicalCaseLibraryDto(saveTypicalCaseLibraryDto);
    }

    /**
     * 删除典型案例库
     *
     * @param deleteTypicalCaseLibraryDto
     * @return
     */
    @InnerAuth
    @PostMapping("/typical/case/library/delete")
    @ApiOperation(value = "删除典型案例库")
    public void deleteTypicalCaseLibraryById(@RequestBody DeleteTypicalCaseLibraryDto deleteTypicalCaseLibraryDto) {
        this.knwTypicalCaseLibraryIssuedService.deleteTypicalCaseLibraryById(deleteTypicalCaseLibraryDto);
    }

    /**
     * 更新典型案例库
     */
    @PostMapping("/typical/case/library/update")
    @InnerAuth
    @ApiOperation(value = "更新典型案例库")
    public void updateTypicalCaseLibrary(@RequestBody SaveTypicalCaseLibraryDto updateTypicalCaseLibrary) {
        this.knwTypicalCaseLibraryIssuedService.updateTypicalCaseLibrary(updateTypicalCaseLibrary);
    }


    /**
     * 增加密评机构信息
     */
    @PostMapping("/evaluate/agency/save")
    @InnerAuth
    @ApiOperation(value = "增加密评机构信息")
    public void saveEvaluateAgency(@RequestBody SaveEvaluateAgencyDto saveEvaluateAgencyDto) {
        this.knwConfidentialEvaluationAgencyIssuedService.saveEvaluateAgency(saveEvaluateAgencyDto);
    }

    /**
     * 更新密评机构信息
     */
    @PostMapping("/evaluate/agency/update")
    @InnerAuth
    @ApiOperation(value = "更新密评机构信息")
    public void updateEvaluateAgency(@RequestBody SaveEvaluateAgencyDto saveEvaluateAgencyDto) {
        this.knwConfidentialEvaluationAgencyIssuedService.updateEvaluateAgency(saveEvaluateAgencyDto);
    }

    /**
     * 更新密评机构信息
     */
    @PostMapping("/evaluate/agency/delete")
    @InnerAuth
    @ApiOperation(value = "删除密评机构信息")
    public void deleteEvaluateAgencyById(@RequestBody DeleteEvaluationAgencyDto deleteEvaluationAgencyDto) {
        this.knwConfidentialEvaluationAgencyIssuedService.deleteEvaluateAgencyById(deleteEvaluationAgencyDto);
    }
//
//    /**
//     * 批量更新或插入密评机构
//     *
//     * @param evaluationAgencyList
//     */
//    @PostMapping("/evaluate/agency/batchInsertOrUpdate")
//    @InnerAuth
//    @ApiOperation(value = "批量更新或插入密评机构")
//    public void batchInsertOrUpdateEvaluateAgency(@RequestBody String reqJson) {
//        this.knwConfidentialEvaluationAgencyIssuedService.batchInsertOrUpdateEvaluateAgency(reqJson);
//    }

    @PostMapping("/secretApplication/yearTask/add")
    @InnerAuth
    @ApiOperation("年度任务新增")
    public void reportAddSecretAppYearTask(@RequestBody SaveCryYearListReportedDto reportedDto) {
        this.yearTaskIssuedService.reportAddSecretAppYearTask(reportedDto);
    }

    @PostMapping("/secretApplication/yearTask/update")
    @InnerAuth
    @ApiOperation("年度任务修改")
    public void reportUpdateSecretAppYearTask(@RequestBody UpdateCryYearListDto updateCryYearListDto) {
        this.yearTaskIssuedService.reportUpdateSecretAppYearTask(updateCryYearListDto);
    }

    @PostMapping("/insert/app")
    @InnerAuth
    @ApiOperation("应用新增")
    public void saveAmgApp(@RequestBody SaveAmgAppDto saveAmgAppDto) {
        this.amgAppIssuedService.saveAmgApp(saveAmgAppDto);
    }

    @PostMapping("/update/app")
    @InnerAuth
    @ApiOperation("应用修改")
    public void updateAmgApp(@RequestBody AmgApp amgApp) {
        this.amgAppIssuedService.updateAmgApp(amgApp);
    }

    @PostMapping("/delete/app")
    @InnerAuth
    @ApiOperation("应用删除")
    public void updateAmgApp(@RequestBody List<String> ids) {
        this.amgAppIssuedService.deleteAmgApp(ids);
    }

    @PostMapping("/insert/device")
    @InnerAuth
    @ApiOperation("应用设备新增")
    public void saveAmgDevice(@RequestBody AmgDevice amgDevice) {
        this.amgDeviceIssuedService.saveAmgDevice(amgDevice);
    }

    @PostMapping("/update/device")
    @InnerAuth
    @ApiOperation("应用设备修改")
    public void updateAmgDevice(@RequestBody AmgDevice amgDevice) {
        this.amgDeviceIssuedService.updateAmgDevice(amgDevice);
    }

    @PostMapping("/delete/device")
    @InnerAuth
    @ApiOperation("应用设备删除")
    public void deleteAmgDevice(@RequestBody List<String> ids) {
        this.amgDeviceIssuedService.deleteAmgDevice(ids);
    }

    @PostMapping("/insert/system")
    @InnerAuth
    public void saveAmgSystem(@RequestBody AmgSystem amgSystem) {
        this.amgSystemIssuedService.saveAmgSystem(amgSystem);
    }

    @PostMapping("/update/system")
    @InnerAuth
    @ApiOperation("应用系统修改")
    public void updateAmgSystem(@RequestBody AmgSystem amgSystem) {
        this.amgSystemIssuedService.updateAmgSystem(amgSystem);
    }

    @PostMapping("/delete/system")
    @InnerAuth
    @ApiOperation("应用系统删除")
    public void deleteAmgSystem(@RequestBody List<String> ids) {
        this.amgSystemIssuedService.deleteAmgSystem(ids);
    }

    @PostMapping("/import/app")
    @InnerAuth
    @ApiOperation("应用档案导入")
    public void importAmgApp(@RequestBody String reqJson) {
        log.info("批量导入应用上报数据:{}", reqJson);
        List<AmgApp> amgAppList = ObjectMapperUtil.jsonToBean(reqJson, new TypeReference<List<AmgApp>>() {
        });
        this.amgAppIssuedService.importAmgApp(amgAppList);
    }

    @PostMapping("/import/device")
    @InnerAuth
    @ApiOperation("应用设备导入")
    public void importAmgDevice(@RequestBody List<AmgDevice> amgDeviceList) {
        this.amgDeviceIssuedService.importAmgDevice(amgDeviceList);
    }

    @PostMapping("/import/system")
    @InnerAuth
    @ApiOperation("应用系统导入")
    public void importAmgSystem(@RequestBody List<AmgSystem> amgSystemList) {
        this.amgSystemIssuedService.importAmgSystem(amgSystemList);
    }

    @PostMapping("/delete/config/app")
    @InnerAuth
    @ApiOperation("应用配置删除")
    public void deleteConfigAmgApp(@Validated @RequestBody DeleteAmgAppConfigDto deleteAmgAppConfigDto) {
        this.amgAppConfigIssuedService.deleteConfigAmgApp(deleteAmgAppConfigDto);
    }

    @PostMapping("/save/config/app")
    @InnerAuth
    @ApiOperation("应用配置新增")
    public void saveConfigAmgApp(@Validated @RequestBody List<SaveAmgAppConfigDto> saveAmgAppConfigDtos) {
        System.out.println(saveAmgAppConfigDtos);
        this.amgAppConfigIssuedService.saveConfigAmgApp(saveAmgAppConfigDtos);
    }

    @PostMapping("/probe")
    @InnerAuth
    @ApiOperation("探针新增")
    public void saveProbe(@RequestBody Probe probe) {
        this.probeIssuedService.saveProbe(probe);
    }

    @PutMapping("/probe")
    @InnerAuth
    @ApiOperation("探针修改")
    public void updateProbe(@RequestBody Probe probe) {
        this.probeIssuedService.updateProbe(probe);
    }

    @DeleteMapping("/probe")
    @InnerAuth
    @ApiOperation(value = "探针批量删除")
    public void deleteProbe(@RequestBody List<String> ids) {
        this.probeIssuedService.deleteProbe(ids);
    }

    @PostMapping("/frontEndProcessor")
    @InnerAuth
    @ApiOperation(value = "前置机新增")
    public void saveFrontEndProcessor(@RequestBody FrontEndProcessor frontEndProcessor) {
        this.frontEndProcessorIssuedService.saveFrontEndProcessor(frontEndProcessor);
    }


    @PutMapping("/frontEndProcessor")
    @ApiOperation(value = "前置机修改")
    @InnerAuth
    public void updateFrontEndProcessor(@RequestBody FrontEndProcessor frontEndProcessor) {
        frontEndProcessorIssuedService.updateFrontEndProcessor(frontEndProcessor);
    }

    @DeleteMapping("/frontEndProcessor/batchDelete")
    @InnerAuth
    @ApiOperation(value = "前置机批量删除")
    public void deleteFrontEndProcessor(@RequestBody List<String> ids) {
        frontEndProcessorIssuedService.deleteFrontEndProcessor(ids);
    }

    @PostMapping("/syncEvaluateAgency")
    @InnerAuth
    @ApiOperation(value = "密评机构同步")
    public void syncEvaluateAgency(@RequestBody Map<String, Object> params) {
        String data = MapUtil.getStr(params, "data");
        String platformCode = MapUtil.getStr(params, "platformCodes");
        List<String> platformCodes= StrUtil.split(platformCode, ",", true, true);
        knwConfidentialEvaluationAgencyIssuedService.syncEvaluateAgency(data,platformCodes);
    }


    @PostMapping("/syncExpertLibrary")
    @InnerAuth
    @ApiOperation(value = "密码专家同步")
    public void syncExpertLibrary(@RequestBody Map<String, Object> params) {
        String data = MapUtil.getStr(params, "data");
        String platformCode = MapUtil.getStr(params, "platformCodes");
        List<String> platformCodes= StrUtil.split(platformCode, ",", true, true);
        knwComExpertLibraryIssuedService.syncExpertLibrary(data,platformCodes);
    }

    @PostMapping("/syncTypicalCaseLibrary")
    @InnerAuth
    @ApiOperation(value = "典型案例同步")
    public void syncTypicalCaseLibrary(@RequestBody Map<String, Object> params) {
        String data = MapUtil.getStr(params, "data");
        String platformCode = MapUtil.getStr(params, "platformCodes");
        List<String> platformCodes= StrUtil.split(platformCode, ",", true, true);
        knwTypicalCaseLibraryIssuedService.syncTypicalCaseLibrary(data,platformCodes);
    }



}
