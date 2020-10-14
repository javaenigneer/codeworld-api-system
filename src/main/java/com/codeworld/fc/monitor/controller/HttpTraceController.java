package com.codeworld.fc.monitor.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.vo.HttpTraceSearchVO;
import com.codeworld.fc.monitor.entity.FcHttpTrace;
import com.codeworld.fc.monitor.service.HttpTraceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName HttpTraceController
 * Description 请求追踪接口管理
 * Author Lenovo
 * Date 2020/10/9
 * Version 1.0
**/
@RestController
@RequestMapping("system-monitor")
@Api(tags = "请求追踪接口管理")
public class HttpTraceController {

    @Autowired(required = false)
    private HttpTraceService httpTraceService;

    @PostMapping("get-http-trace")
    @ApiOperation("查询请求")
    @ControllerEndpoint(operation = "查询请求",exceptionMessage = "查询请求失败")
    public FCResponse<List<FcHttpTrace>> getHttpTrace(@RequestBody HttpTraceSearchVO httpTraceSearchVO){
        return this.httpTraceService.getHttpTrace(httpTraceSearchVO);
    }
}
