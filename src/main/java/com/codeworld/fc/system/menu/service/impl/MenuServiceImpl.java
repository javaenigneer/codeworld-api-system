package com.codeworld.fc.system.menu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.dto.MenuRequestDTO;
import com.codeworld.fc.system.menu.dto.MenuTreeNode;
import com.codeworld.fc.system.menu.entity.Menu;
import com.codeworld.fc.system.menu.mapper.MenuMapper;
import com.codeworld.fc.system.menu.service.MenuService;
import com.codeworld.fc.utils.IDGeneratorUtil;
import com.codeworld.fc.utils.TreeBuilder;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName MenuServiceImpl
 * Description 菜单接口Service
 * Author Lenovo
 * Date 2020/8/26
 * Version 1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired(required = false)
    private MenuMapper menuMapper;

    /**
     * 获取菜单树
     *
     * @return
     */
    @Override
    public FCResponse<Object> treeMenu() {

        List<Menu> menus = this.menuMapper.getAllMenu();

        List<MenuTreeNode> menuTreeNodeList = Lists.newArrayList();

        if (!CollectionUtils.isEmpty(menus)) {
            menus.forEach(menu -> {
                MenuTreeNode menuTreeNode = new MenuTreeNode();
                BeanUtils.copyProperties(menu, menuTreeNode);
                menuTreeNodeList.add(menuTreeNode);
            });
        }

        List<MenuTreeNode> newMenuTreeNodeList = TreeBuilder.buildMenuTree(menuTreeNodeList);

        newMenuTreeNodeList.stream().sorted(Comparator.comparing(MenuTreeNode::getSortNo)).collect(Collectors.toList());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("menuList", menus);

        jsonObject.put("menuTree", newMenuTreeNodeList);

        return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.menu.MENU_GET_SUCCESS.getMsg(), jsonObject);
    }

    /**
     * 修改菜单
     *
     * @param menuRequestDTO
     * @return
     */
    @Override
    public FCResponse<Void> updateMenu(MenuRequestDTO menuRequestDTO) {

        try {

            Menu menu = new Menu();

            BeanUtil.copyProperties(menuRequestDTO, menu);

            menu.setUpdateTime(new Date());

            this.menuMapper.updateMenu(menu);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.menu.MENU_UPDATE_SUCCESS.getMsg());
        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 添加菜单
     *
     * @param menuRequestDTO
     * @return
     */
    @Override
    public FCResponse<Void> addMenu(MenuRequestDTO menuRequestDTO) {

        try {

            Menu menu = new Menu();

            BeanUtil.copyProperties(menuRequestDTO, menu);

            menu.setId(IDGeneratorUtil.getNextId());

            menu.setCreateTime(new Date());

            menu.setUpdateTime(menu.getCreateTime());

            this.menuMapper.addMenu(menu);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.menu.MENU_ADD_SUCCESS.getMsg());

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public FCResponse<Void> deleteMenu(Long id) {

        if (id == null || id <= 0) {
            return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(), HttpMsg.menu.MENU_PARAM_ERROR.getMsg());
        }

        try {

            // 先查询菜单信息
            Menu menu = this.menuMapper.getMenuById(id);

            List<Long> ids = new ArrayList<>();

            // 判断菜单类型
            if (StringUtils.equalsIgnoreCase("menu",menu.getType())){
                // menu
                // 1.先查询自己的子菜单
                List<Menu> menus = this.menuMapper.getChildMenuById(menu.getId());
                // 没有子集菜单
                if (CollectionUtils.isEmpty(menus)){
                    // 直接删除角色关联的菜单
                    ids.add(menu.getId());
                    this.menuMapper.deleteRoleMenu(ids);
                    // 删除菜单信息
                    this.menuMapper.deleteMenuById(menu.getId());
                }else {
                    // 有子集菜单
                   ids = menus.stream().map(menuItem ->{
                       return menuItem.getId();
                   }).collect(Collectors.toList());

                   ids.add(menu.getId());
                   // 执行删除
                    this.menuMapper.deleteRoleMenu(ids);
                    // 删除子集菜单
                    this.menuMapper.deleteChildMenu(menu.getId());
                    // 删除菜单信息
                    this.menuMapper.deleteMenuById(menu.getId());
                }
            }
            if (StringUtils.equalsIgnoreCase("button",menu.getType())){
                // button
                // 1.删除角色关联的菜单信息
                ids.add(menu.getId());
                this.menuMapper.deleteRoleMenu(ids);
                // 2.删除菜单信息
                this.menuMapper.deleteMenuById(menu.getId());
            }
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.menu.MENU_DELETE_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }
}
