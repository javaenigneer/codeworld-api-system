package com.codeworld.fc.common.authority;

import com.codeworld.fc.common.enums.StatusEnum;
import com.codeworld.fc.common.exception.FCException;
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
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;

    private final String USER_INFO = "USER_INFO:USER:ID:";


    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = this.userMapper.getUserByName(username);

        // 判断用户是否已登录
        if (this.stringRedisTemplate.hasKey(USER_INFO + user.getUserId())) {
            throw new FCException("用户已登录或下线中，请稍等20秒再登录");
        }

        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 判断用户是否被禁用
        if (user.getUserStatus() == StatusEnum.USER_DISABLE) {
            throw new DisabledException("已禁用，请联系管理员");
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
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(username, new BCryptPasswordEncoder().encode("123456"), grantedAuthorities);
    }
}
