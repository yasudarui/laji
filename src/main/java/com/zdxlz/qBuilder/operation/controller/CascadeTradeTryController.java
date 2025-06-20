package com.zdxlz.qBuilder.operation.controller;


import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeTryDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeTry;
import com.zdxlz.qBuilder.common.core.web.controller.BaseController;
import com.zdxlz.qBuilder.common.core.web.page.TableDataInfo;
import com.zdxlz.qBuilder.common.security.annotation.RequiresPermissions;
import com.zdxlz.qBuilder.operation.service.CascadeTradeTryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangtao
 * @description 交易重试controller
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/cascade/trade/try")
@Api(tags = "两级互联交易重试")
public class CascadeTradeTryController extends BaseController {

    @Resource
    private CascadeTradeTryService cascadeTradeTryService;

    /**
     * 分页查询两级互联重试列表
     * @param queryPageCascadeTradeTryDto 两级互联交易重试列表分页查询入参
     * @return 分页查询两级互联重试记录
     */
    @GetMapping("/page")
    @RequiresPermissions("cascade:trade:try:page")
    @ApiOperation(value = "两级互联交易重试列表分页查询")
    public TableDataInfo<CascadeTradeTry> page(QueryPageCascadeTradeTryDto queryPageCascadeTradeTryDto) {
        startPage();
        List<CascadeTradeTry> result = cascadeTradeTryService.listCascadeTradeTrys(queryPageCascadeTradeTryDto);
        return getDataTable(result);
    }

}
