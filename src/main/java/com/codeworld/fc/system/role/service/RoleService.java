package com.codeworld.fc.system.role.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.entity.RoleMenu;
import com.codeworld.fc.system.role.entity.Role;
import com.codeworld.fc.system.role.vo.RoleAddRequest;
import com.codeworld.fc.system.role.vo.RoleMenuRequest;
import com.codeworld.fc.system.role.vo.RoleSearchVO;
import com.codeworld.fc.system.role.vo.RoleUpdateRequest;

import java.util.List;

public interface RoleService {

    /**
     * 获取全部角色信息
     * @param roleSearchVO
     * @return
     */
    FCResponse<DataResponse<List<Role>>> getAllRole(RoleSearchVO roleSearchVO);

    /**
     * 获取角色菜单
     * @param roleId
     * @return
     */
    FCResponse<List<RoleMenu>> getRoleMenu(Long roleId);

    /**
     * 修改角色
     * @param roleUpdateRequest
     * @return
     */
    FCResponse<Void> updateRole(RoleUpdateRequest roleUpdateRequest);

    /**
     * 设置角色菜单
     * @param roleMenuRequest
     * @return
     */
    FCResponse<Void> addRoleMenu(RoleMenuRequest roleMenuRequest);

    /**
     * 添加角色
     * @param roleAddRequest
     * @return
     */
    FCResponse<Void> addRole(RoleAddRequest roleAddRequest);

    /**
     * 获取全部角色--无参数
     * @return
     */
    FCResponse<List<Role>> getAllRoleNoParam();

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    FCResponse<Void> deleteRole(Long roleId);
}
