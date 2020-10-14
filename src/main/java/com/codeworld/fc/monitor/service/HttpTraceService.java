package com.codeworld.fc.monitor.service;

import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.vo.HttpTraceSearchVO;
import com.codeworld.fc.monitor.entity.FcHttpTrace;

import java.util.List;

public interface HttpTraceService {
    /**
     * 获取请求追踪
     * @return
     */
    FCResponse<List<FcHttpTrace>> getHttpTrace(HttpTraceSearchVO httpTraceSearchVO);
}
