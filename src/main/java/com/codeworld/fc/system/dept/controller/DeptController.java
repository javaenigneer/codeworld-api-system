package com.codeworld.fc.system.dept.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.dept.dto.DeptRequestDTO;
import com.codeworld.fc.system.dept.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * ClassName DeptController
 * Description 部门管理接口
 * Author Lenovo
 * Date 2020/10/13
 * Version 1.0
**/
@RestController
@RequestMapping("system-dept")
@Api(tags = "部门管理接口")
public class DeptController {

    @Autowired(required = false)
    private DeptService deptService;

    @PostMapping("tree-dept")
    @ApiOperation("获取部门树")
    @ControllerEndpoint(operation = "获取部门树",exceptionMessage = "获取部门树失败")
    public FCResponse<Object> treeDept(){
        return this.deptService.treeDept();
    }

    @PostMapping("add-dept")
    @ApiOperation("添加部门")
    @ControllerEndpoint(operation = "添加部门",exceptionMessage = "添加部门失败")
    public FCResponse<Void> addDept(@RequestBody @Valid DeptRequestDTO deptRequestDTO){
      return this.deptService.addDept(deptRequestDTO);
    }

    @PostMapping("update-dept")
    @ApiOperation("修改部门")
    @ControllerEndpoint(operation = "修改部门",exceptionMessage = "修改部门失败")
    public FCResponse<Void> updateDept(@RequestBody @Valid DeptRequestDTO deptRequestDTO){
        return this.deptService.updateDept(deptRequestDTO);
    }

}
