package com.codeworld.fc.system.menu.controller;

import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.dto.MenuRequestDTO;
import com.codeworld.fc.system.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ClassName MenuController
 * Description 菜单接口管理
 * Author Lenovo
 * Date 2020/8/26
 * Version 1.0
 **/
@RestController
@RequestMapping("system-menu")
@Api(tags = "菜单接口管理")
public class MenuController {

    @Autowired(required = false)
    private MenuService menuService;

    @PostMapping("tree-menu")
    @ApiOperation("获取菜单树")
    public FCResponse<Object> treeMenu() {
        return this.menuService.treeMenu();
    }

    @PostMapping("update-menu")
    @ApiOperation("修改菜单")
    @PreAuthorize("hasAuthority('menu:edit')")
    public FCResponse<Void> updateMenu(@RequestBody @Valid MenuRequestDTO menuRequestDTO) {
        return this.menuService.updateMenu(menuRequestDTO);
    }

    @PostMapping("add-menu")
    @ApiOperation("添加菜单")
    @PreAuthorize("hasAuthority('menu:add')")
    public FCResponse<Void> addMenu(@RequestBody @Valid MenuRequestDTO menuRequestDTO) {
        return this.menuService.addMenu(menuRequestDTO);
    }

    @PostMapping("delete-menu")
    @ApiOperation("删除菜单")
    @PreAuthorize("hasAuthority('menu:delete')")
    public FCResponse<Void> deleteMenu(@RequestParam("id") Long id) {
        return this.menuService.deleteMenu(id);
    }
}
