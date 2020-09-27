package com.codeworld.fc.system.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.codeworld.fc.common.authority.JWTUtil;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.enums.StatusEnum;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.entity.Menu;
import com.codeworld.fc.system.menu.mapper.MenuMapper;
import com.codeworld.fc.system.role.entity.Role;
import com.codeworld.fc.system.role.entity.UserRole;
import com.codeworld.fc.system.role.mapper.RoleMapper;
import com.codeworld.fc.system.user.dto.UserInfoResponse;
import com.codeworld.fc.system.user.entity.User;
import com.codeworld.fc.system.user.mapper.UserMapper;
import com.codeworld.fc.system.user.service.UserService;
import com.codeworld.fc.system.user.vo.*;

import com.codeworld.fc.utils.CookieUtils;
import com.codeworld.fc.utils.IDGeneratorUtil;
import com.codeworld.fc.utils.JsonUtils;
import com.codeworld.fc.utils.TreeBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName UserServiceImpl
 * Description 用户接口Service
 * Author Lenovo
 * Date 2020/8/12
 * Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Autowired(required = false)
    private MenuMapper menuMapper;

    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final String USER_INFO = "USER_INFO";
    /**
     * 获取全部用户
     *
     * @return
     */
    @Override
    public FCResponse<DataResponse<List<User>>> getAllUser(UserSearchRequest userSearchRequest) {

        PageHelper.startPage(userSearchRequest.getPage(), userSearchRequest.getLimit());

        LOGGER.info("数据：" + userSearchRequest);

        List<User> users = this.userMapper.getAllUser(userSearchRequest);

        if (CollectionUtils.isEmpty(users)) {

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), "数据为空", DataResponse.dataResponse(users,0L));

        }

        PageInfo<User> pageInfo = new PageInfo<>(users);

        return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), "查询成功", DataResponse.dataResponse(pageInfo.getList(),pageInfo.getTotal()));
    }

    /**
     * 修改用户状态
     *
     * @param userId
     * @param userStatus
     * @return
     */
    @Override
    public FCResponse<Void> updateUserStatus(Long userId, Integer userStatus) {

        if (userId == null || userId <= 0) {

            return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(), "参数错误");
        }

        try {

            User user = new User();

            user.setUserId(userId);

            user.setUserStatus(userStatus);

            this.userMapper.updateUserStatus(user);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_STATUS_SUCCESS.getMsg());

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public FCResponse<Void> deleteUser(Long userId) {

        if (userId == null || userId <= 0) {
            return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(), HttpMsg.user.USER_ID_ERROR.getMsg());
        }

        try {

            // 先删除用户关联的角色
            this.roleMapper.deleteUserRoleByUserId(userId);
            // 删除用户
            this.userMapper.deleteUser(userId);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_DELETE_SUCCESS.getMsg());

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 添加用户
     *
     * @param userRegisterRequest
     * @return
     */
    @Override
    public FCResponse<Void> addUser(UserRegisterRequest userRegisterRequest) {

        try {

            // 根据用户名获取用户
            User existUser = this.userMapper.getUserByName(userRegisterRequest.getUserName());

            if (existUser != null) {

                return FCResponse.dataResponse(HttpFcStatus.DATAEXIST.getCode(), HttpMsg.user.USER_EXIST.getMsg());

            }

            User user = new User();

            BeanUtil.copyProperties(userRegisterRequest, user);

            user.setUserId(IDGeneratorUtil.getUserId());

            user.setCreateTime(new Date());

            user.setUpdateTime(user.getCreateTime());

            this.userMapper.addUser(user);

            // 设置用户类型
            UserRole userRole = new UserRole();

            userRole.setUserRoleId(IDGeneratorUtil.getNextId());

            userRole.setUserId(user.getUserId());

            userRole.setRoleId(userRegisterRequest.getRoleType());

            userRole.setCreateTime(new Date());

            userRole.setUpdateTime(userRole.getCreateTime());

            this.roleMapper.addUserRole(userRole);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_ADD_SUCCESS.getMsg());

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 修改用户
     *
     * @param userUpdateRequest
     * @return
     */
    @Override
    public FCResponse<Void> updateUser(UserUpdateRequest userUpdateRequest) {

        try {

            User user = new User();

            user.setUserId(userUpdateRequest.getUserId());

            user.setUserName(userUpdateRequest.getUserName());

            user.setUserEmail(userUpdateRequest.getUserEmail());

            user.setUserPhone(userUpdateRequest.getUserPhone());

            user.setUpdateTime(new Date());

            user.setUserStatus(userUpdateRequest.getUserStatus());

            this.userMapper.updateUser(user);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_UPDATE_SUCCESS.getMsg());

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 根据用户名获取用户
     *
     * @param userName
     * @return
     */
    @Override
    public FCResponse<User> getUserByName(String userName) {

        try {

            User user = this.userMapper.getUserByName(userName);

            if (user != null) {

                return FCResponse.dataResponse(HttpFcStatus.DATAEXIST.getCode(), HttpMsg.user.USER_EXIST.getMsg());
            }

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_NAME_RIGHT.getMsg());
        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public FCResponse<UserInfoResponse> getUserInfo(UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response) {

        try {


//            // 判断Token是否失效
//            String token = CookieUtils.getCookieValue(request, "Admin-Token");
//
//            // 判断Token是否一致 // 认证失败
//            if (!StringUtils.equals(userLoginRequest.getToken(), token)) {
//
//                return FCResponse.dataResponse(HttpFcStatus.AUTHFAILCODE.getCode(), HttpMsg.user.USER_AUTH_ERROR.getMsg(), null);
//            }

//            Boolean flag = this.stringRedisTemplate.hasKey(USER_INFO);
//
//            if (flag){
//                // redis缓存中有用户数据,从redis中获取
//                String json = this.stringRedisTemplate.opsForValue().get(USER_INFO);
//
//                UserInfoResponse userInfoResponse = JsonUtils.parse(json, UserInfoResponse.class);
//
//                // 重新设置时间
//                this.stringRedisTemplate.opsForValue().set(USER_INFO,json,60 * 10,TimeUnit.SECONDS);
//
//                return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.user.USER_AUTH_SUCCESS.getMsg(),userInfoResponse);
//
//            }
            // 根据Token获取用户名
            String userName = JWTUtil.getUserName(userLoginRequest.getToken());

            // 根据用户名获取用户信息
            User user = this.userMapper.getUserByName(userName);

            // 用户不存在
            if (user == null) {

                return FCResponse.dataResponse(HttpFcStatus.AUTHFAILCODE.getCode(), HttpMsg.user.USER_AUTH_ERROR.getMsg(), null);
            }

            UserInfoResponse userInfoResponse = new UserInfoResponse();

            Set<String> roles = new HashSet();

            Set<MenuVO> menuVOS = new HashSet<>();

            Set<ButtonVO> buttonVOS = new HashSet<>();

            BeanUtil.copyProperties(user, userInfoResponse);

            // 获取用户角色--根据用户Id
            Role role = this.roleMapper.getRoleById(user.getUserId());

            if (role == null){

                return FCResponse.dataResponse(HttpFcStatus.VALIDATEFAILCODE.getCode(),HttpMsg.user.USER_AUTH_ERROR.getMsg());
            }

            roles.add(role.getRoleCode());

            // 根据角色Id获取菜单权限
            List<Menu> menus = this.menuMapper.getMenuByRoleId(role.getRoleId());

            // 菜单不为空
            if (!CollectionUtils.isEmpty(menus)) {

                menus.stream().filter(Objects::nonNull).forEach(menu -> {

                    // 按钮权限
                    if (StringUtils.equalsIgnoreCase("button", menu.getType())) {

                        // 添加到按钮权限
                        ButtonVO buttonVO = new ButtonVO();

                        BeanUtil.copyProperties(menu, buttonVO);

                        buttonVOS.add(buttonVO);
                    }

                    // 菜单权限
                    if (StringUtils.equalsIgnoreCase("menu", menu.getType())) {

                        // 添加到菜单权限
                        MenuVO menuVO = new MenuVO();

                        BeanUtil.copyProperties(menu, menuVO);

                        menuVOS.add(menuVO);
                    }

                });
            }

            userInfoResponse.getRoles().addAll(roles);

            userInfoResponse.getButtons().addAll(buttonVOS);

            userInfoResponse.getMenus().addAll(TreeBuilder.buildTree(menuVOS));

//            // 保存到Redis中
//            String json = JsonUtils.serialize(userInfoResponse);
//
//            this.stringRedisTemplate.opsForValue().set(USER_INFO,json, 60 * 10, TimeUnit.SECONDS);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_AUTH_SUCCESS.getMsg(), userInfoResponse);

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }
}
