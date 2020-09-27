package com.codeworld.fc.system.menu.mapper;

import com.codeworld.fc.system.menu.entity.Menu;
import com.codeworld.fc.system.menu.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuMapper {
    /**
     * 查询全部菜单
     * @return
     */
    List<Menu> getAllMenu();

    /**
     * 根据角色Id获取菜单权限
     * @param roleId
     * @return
     */
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * 修改菜单
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 添加菜单
     * @param menu
     */
    void addMenu(Menu menu);

    /**
     * 根据菜单Id获取菜单信息
     * @param id
     * @return
     */
    Menu getMenuById(Long id);

    /**
     * 根据Id获取子集菜单
     * @param id
     * @return
     */
    List<Menu> getChildMenuById(Long id);

    /**
     * 删除角色菜单信息
     * @param ids
     */
    void deleteRoleMenu(List<Long> ids);

    /**
     * 删除菜单信息
     * @param id
     */
    void deleteMenuById(Long id);

    /**
     * 删除子集菜单
     * @param id
     */
    void deleteChildMenu(Long id);

    /**
     * 删除角色菜单
     * @param roleId
     */
    void deleteRoleMenuByRoleId(Long roleId);

    /**
     * 设置角色菜单
     */
    void addRoleMenu(RoleMenu roleMenu);
}

