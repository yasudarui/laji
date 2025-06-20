package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.DeleteTypicalCaseLibraryDto;
import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.SaveTypicalCaseLibraryDto;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1 * @Author: zhongjian.zhang
 * 2 * @Date: 2024/10/29 9:31 上午
 * 3
 */
@Service
public class KnwTypicalCaseLibraryIssuedService extends DataOutCascadeTradeAbs {

    public KnwTypicalCaseLibraryIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode();
        this.bizType = BizTypeEnum.KNW_TYPICAL_CASE_ISSUED.getCode();
    }


    public void saveTypicalCaseLibraryDto(SaveTypicalCaseLibraryDto saveTypicalCaseLibraryDto) {
        super.send(saveTypicalCaseLibraryDto, BusinessType.INSERT);
    }

    public void updateTypicalCaseLibrary(SaveTypicalCaseLibraryDto updateTypicalCaseLibrary) {
        super.send(updateTypicalCaseLibrary, BusinessType.UPDATE);
    }

    public void deleteTypicalCaseLibraryById(DeleteTypicalCaseLibraryDto deleteTypicalCaseLibraryDto) {
        super.send(deleteTypicalCaseLibraryDto, BusinessType.DELETE);
    }

    public void syncTypicalCaseLibrary(String data, List<String> platformCodes) {
        super.send(data, BusinessType.ASYNC,platformCodes.toArray(new String[0]));
    }
}
