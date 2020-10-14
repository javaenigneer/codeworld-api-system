package com.codeworld.fc.monitor.service.impl;

import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.vo.HttpTraceSearchVO;
import com.codeworld.fc.monitor.endpoint.FcHttpTraceEndPoint;
import com.codeworld.fc.monitor.entity.FcHttpTrace;
import com.codeworld.fc.monitor.service.HttpTraceService;
import com.codeworld.fc.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName HttpTraceServiceImpl
 * Description TODO
 * Author Lenovo
 * Date 2020/10/9
 * Version 1.0
**/
@Service
public class HttpTraceServiceImpl implements HttpTraceService {

    @Autowired(required = false)
    private FcHttpTraceEndPoint fcHttpTraceEndPoint;
    /**
     * 获取请求追踪
     *
     * @return
     */
    @Override
    public FCResponse<List<FcHttpTrace>> getHttpTrace(HttpTraceSearchVO httpTraceSearchVO) {

        FcHttpTraceEndPoint.FcHttpTraceDescriptor traces = this.fcHttpTraceEndPoint.traces();

        List<HttpTrace> httpTraceList = traces.getTraces();

        List<FcHttpTrace> fcHttpTraceList = new ArrayList<>();

        httpTraceList.forEach(httpTrace -> {

            // 创建对象
            FcHttpTrace fcHttpTrace = new FcHttpTrace();

            fcHttpTrace.setRequestTime(DateUtils.formatInstant(httpTrace.getTimestamp(),"yyyy-MM-dd HH:mm:ss"));

            fcHttpTrace.setMethod(httpTrace.getRequest().getMethod());

            fcHttpTrace.setStatus(httpTrace.getResponse().getStatus());

            fcHttpTrace.setTimeTaken(httpTrace.getTimeTaken());

            fcHttpTrace.setUrl(httpTrace.getRequest().getUri());

            if (StringUtils.isNotBlank(httpTraceSearchVO.getMethod()) && StringUtils.isNotBlank(httpTraceSearchVO.getUrl())) {
                if (StringUtils.equalsIgnoreCase(httpTraceSearchVO.getMethod(), fcHttpTrace.getMethod())
                        && StringUtils.containsIgnoreCase(fcHttpTrace.getUrl().toString(), httpTraceSearchVO.getUrl()))
                    fcHttpTraceList.add(fcHttpTrace);
            } else if (StringUtils.isNotBlank(httpTraceSearchVO.getMethod())) {
                if (StringUtils.equalsIgnoreCase(httpTraceSearchVO.getMethod(), fcHttpTrace.getMethod()))
                    fcHttpTraceList.add(fcHttpTrace);
            } else if (StringUtils.isNotBlank(httpTraceSearchVO.getUrl())) {
                if (StringUtils.containsIgnoreCase(fcHttpTrace.getUrl().toString(), httpTraceSearchVO.getUrl()))
                    fcHttpTraceList.add(fcHttpTrace);
            } else {
                fcHttpTraceList.add(fcHttpTrace);
            }
        });

        return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.http.HTTP_DATA_SUCCESS.getMsg(),fcHttpTraceList);
    }
}
