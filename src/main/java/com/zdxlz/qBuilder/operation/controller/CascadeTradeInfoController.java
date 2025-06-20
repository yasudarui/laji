package com.zdxlz.qBuilder.operation.controller;


import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeInfoDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.common.core.web.controller.BaseController;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.core.web.page.TableDataInfo;
import com.zdxlz.qBuilder.common.security.annotation.RequiresPermissions;
import com.zdxlz.qBuilder.operation.service.CascadeTradeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangtao
 * @description 交易信息controller
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/cascade/trade/info")
@Api(tags = "两级互联交易列表")
public class CascadeTradeInfoController extends BaseController {

    @Resource
    private CascadeTradeInfoService cascadeTradeInfoService;

    /**
     * 分页查询两级互联交易信息
     * @param queryPageCascadeTradeInfoDto 两级互联交易列表分页查询入参
     * @return
     */
    @GetMapping("/page")
    @RequiresPermissions("cascade:trade:info:page")
    @ApiOperation(value = "两级互联交易列表分页查询")
    public TableDataInfo<CascadeTradeInfo> page( QueryPageCascadeTradeInfoDto queryPageCascadeTradeInfoDto) {
        startPage();
        List<CascadeTradeInfo> result = cascadeTradeInfoService.listCascadeTradeInfos(queryPageCascadeTradeInfoDto);
        return getDataTable(result);
    }

    @GetMapping("/detail/{tradeId}")
    @RequiresPermissions("cascade:trade:info:detail")
    @ApiOperation(value = "两级互联交易详情")
    public AjaxResult getCascadeTradeInfoDetailByTradeId(@PathVariable("tradeId") String tradeId) {
        CascadeTradeInfo cascadeTradeInfo = cascadeTradeInfoService.getCascadeTradeInfoDetailByTradeId(tradeId);
        return AjaxResult.success(cascadeTradeInfo);
    }


    @GetMapping("/dropDown")
    @ApiOperation(value = "两级互联交易列表下拉框")
    public AjaxResult getCascadeTradeInfoDropDown() {
        return AjaxResult.success(cascadeTradeInfoService.getCascadeTradeInfoDropDown());
    }
}
