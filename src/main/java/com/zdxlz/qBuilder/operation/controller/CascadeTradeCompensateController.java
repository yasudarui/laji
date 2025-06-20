package com.zdxlz.qBuilder.operation.controller;


import com.zdxlz.qBuilder.common.core.domain.operation.dto.AbandonCompensateDto;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.CompensateCascadeTradeDto;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeTryDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeCompensate;
import com.zdxlz.qBuilder.common.core.web.controller.BaseController;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.core.web.page.TableDataInfo;
import com.zdxlz.qBuilder.common.log.annotation.Log;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.common.security.annotation.RequiresPermissions;
import com.zdxlz.qBuilder.operation.service.CascadeTradeCompensateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author zhangtao
 * @description 交易补偿controller
 * @date 2024-10-24
 */
@RestController
@RequestMapping("/cascade/trade/compensate")
@Api(tags = "两级互联交易补偿")
public class CascadeTradeCompensateController extends BaseController {

    @Resource
    private CascadeTradeCompensateService cascadeTradeCompensateService;

    /**
     * 分页查询两级互联补偿列表
     * @param queryPageCascadeTradeTryDto 两级互联交易补偿列表分页查询入参
     * @return 分页查询两级互联补偿记录
     */
    @GetMapping("/page")
    @RequiresPermissions("cascade:trade:compensate:page")
    @ApiOperation(value = "两级互联交易补偿列表分页查询")
    public TableDataInfo<CascadeTradeCompensate> page(QueryPageCascadeTradeTryDto queryPageCascadeTradeTryDto) {
        startPage();
        List<CascadeTradeCompensate> result = cascadeTradeCompensateService.listCascadeTradeCompensates(queryPageCascadeTradeTryDto);
        return getDataTable(result);
    }


    @PostMapping("/compensate")
    @Log(title = "两级交易互联补偿", businessType = BusinessType.OTHER)
    @RequiresPermissions("cascade:trade:compensate:compensate")
    @ApiOperation(value = "补偿")
    public AjaxResult compensate(@Valid @RequestBody CompensateCascadeTradeDto compensateCascadeTradeDto) {
        return toAjax(cascadeTradeCompensateService.compensate(compensateCascadeTradeDto));
    }

    @PostMapping("/abandonCompensate")
    @Log(title = "无需补偿", businessType = BusinessType.OTHER)
    @RequiresPermissions("cascade:trade:compensate:abandonCompensate")
    @ApiOperation(value = "无需补偿")
    public AjaxResult abandonCompensate(@Valid @RequestBody AbandonCompensateDto abandonCompensateDto) {
        return toAjax(cascadeTradeCompensateService.abandonCompensate(abandonCompensateDto));
    }
}
