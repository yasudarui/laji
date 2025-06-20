package com.zdxlz.qBuilder.operation.service;

import com.zdxlz.qBuilder.common.core.domain.cascade.CascadePlatform;

import java.util.List;

public interface CascadePlatformService {

    List<CascadePlatform> list(List<String> platformCodeList);
}
