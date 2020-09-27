package com.codeworld.fc.common.authority;

import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.menu.entity.Menu;
import com.codeworld.fc.system.menu.mapper.MenuMapper;
import com.codeworld.fc.system.role.entity.Role;
import com.codeworld.fc.system.role.mapper.RoleMapper;
import com.codeworld.fc.system.user.entity.User;
import com.codeworld.fc.system.user.mapper.UserMapper;
import com.codeworld.fc.system.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName UserDetailServiceImpl
 * Description 用户登录认证信息查询
 * Author Lenovo
 * Date 2020/9/27
 * Version 1.0
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Autowired(required = false)
    private MenuMapper menuMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userMapper.getUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 查询用户的权限
        // 查询用户角色
        Role role = this.roleMapper.getRoleById(user.getUserId());
        if (role == null) {
            throw new UsernameNotFoundException("用户不存在角色");
        }
        // 查询用户权限
        List<Menu> menus = this.menuMapper.getMenuByRoleId(role.getRoleId());
        if (CollectionUtils.isEmpty(menus)) {
            throw new UsernameNotFoundException("用户权限为空");
        }
        Set<String> permissions = new HashSet<>();
        permissions = menus.stream().map(Menu::getResources).collect(Collectors.toSet());
        LOGGER.info("用户权限：" + permissions);
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(username,new BCryptPasswordEncoder().encode("123456"),grantedAuthorities);
    }
}
