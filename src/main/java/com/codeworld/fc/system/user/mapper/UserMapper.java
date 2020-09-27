package com.codeworld.fc.system.user.mapper;

import com.codeworld.fc.system.user.entity.User;
import com.codeworld.fc.system.user.vo.UserSearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 获取全部用户
     * @return
     */
    List<User> getAllUser(UserSearchRequest userSearchRequest);

    /**
     * 修改用户状态
     * @param user
     */
    void updateUserStatus(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User getUserByName(String userName);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);
}
