package com.codeworld.fc.system.role.mapper;

import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.entity.RoleMenu;
import com.codeworld.fc.system.role.entity.Role;
import com.codeworld.fc.system.role.entity.UserRole;
import com.codeworld.fc.system.role.vo.RoleSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {


    /**
     * 根据用户Id获取用户角色信息
     * @param userId
     * @return
     */
    Role getRoleById(Long userId);

    /**
     * 获取全部角色信息
     * @param roleSearchVO
     * @return
     */
    List<Role> getAllRole(RoleSearchVO roleSearchVO);

    /**
     * 获取角色菜单
     * @param roleId
     * @return
     */
    List<RoleMenu> getRoleMenuByRoleId(Long roleId);

    /**
     * 修改角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 获取全部角色--无参数
     * @return
     */
    List<Role> getAllRoleNoParam();

    /**
     * 设置用户角色
     * @param userRole
     */
    void addUserRole(UserRole userRole);

    /**
     * 删除用户关联的角色
     * @param userId
     */
    void deleteUserRoleByUserId(Long userId);

    /**
     * 删除角色关联的用户
     * @param roleId
     */
    void deleteUserRoleByRoleId(Long roleId);

    /**
     * 删除角色信息
     * @param roleId
     */
    void deleteRoleByRoleId(Long roleId);
}
