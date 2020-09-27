package com.codeworld.fc.system.menu.service;

import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.dto.MenuRequestDTO;

public interface MenuService {
    /**
     * 获取菜单树
     * @return
     */
    FCResponse<Object> treeMenu();

    /**
     * 修改菜单
     * @param menuRequestDTO
     * @return
     */
    FCResponse<Void> updateMenu(MenuRequestDTO menuRequestDTO);

    /**
     * 添加菜单
     * @param menuRequestDTO
     * @return
     */
    FCResponse<Void> addMenu(MenuRequestDTO menuRequestDTO);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    FCResponse<Void> deleteMenu(Long id);
}
