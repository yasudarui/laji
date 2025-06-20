package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.DeleteComExpertLibraryDto;
import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.SaveComExpertLibraryDto;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1 * @Author: zhongjian.zhang
 * 2 * @Date: 2024/10/29 9:25 上午
 * 3
 */
@Service
public class KnwComExpertLibraryIssuedService extends DataOutCascadeTradeAbs {

    public KnwComExpertLibraryIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode();
        this.bizType = BizTypeEnum.KNW_EXPERT_ISSUED.getCode();
    }

    public void saveComExpertLibrary(SaveComExpertLibraryDto saveComExpertLibraryDto) {
        super.send(saveComExpertLibraryDto, BusinessType.INSERT);
    }

    public void updateComExpertLibrary(SaveComExpertLibraryDto saveComExpertLibraryDto) {
        super.send(saveComExpertLibraryDto, BusinessType.UPDATE);
    }

    public void deleteComExpertLibrary(DeleteComExpertLibraryDto deleteComExpertLibraryDto) {
        super.send(deleteComExpertLibraryDto, BusinessType.DELETE);
    }

    public void syncExpertLibrary(String data, List<String> platformCodes) {
        super.send(data, BusinessType.ASYNC,platformCodes.toArray(new String[0]));
    }


}
