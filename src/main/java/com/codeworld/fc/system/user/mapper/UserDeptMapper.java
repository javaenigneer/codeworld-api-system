package com.codeworld.fc.system.user.mapper;

import com.codeworld.fc.system.user.entity.UserDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDeptMapper {

    /**
     * 添加用户部门
     * @param userDept
     */
    void addUserDept(UserDept userDept);

    /**
     * 根据用户Id获取部门Id
     * @param userId
     * @return
     */
    List<UserDept> getDeptIdByUserId(Long userId);

    /**
     * 根据用户Id删除用户部门
     *
     * @param userId
     */
    void deleteUserDeptByUserId(Long userId);
}
