package com.hhly.smartdata.security;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.MenuService;
import com.hhly.smartdata.service.authentication.PermissionService;
import com.hhly.smartdata.service.authentication.RoleService;
import com.hhly.smartdata.service.authentication.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private MenuService menuService;

    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute(SysConstant.SESSION_USER);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(user != null){
            Set<String> roleNames = (Set<String>) session.getAttribute(SysConstant.SESSION_USER_ROLES);
            Set<String> permSet = (Set<String>) session.getAttribute(SysConstant.SESSION_USER_PERMS);
            info.setRoles(roleNames);
            info.setStringPermissions(permSet);
        }else{
            if(principalCollection == null){
                throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
            }
            String username = (String) getAvailablePrincipal(principalCollection);
            user = userService.getUserByUsername(username);
            if(user == null) return null;
            //放入用户
            session.setAttribute(SysConstant.SESSION_USER, user);

            //获取角色
            List<Role> roles = roleService.getRolesByUserId(user.getUserId());
            System.out.println("roles:" + roles);
            List<Integer> roleIds = Lists.newArrayList();
            Set<String> roleNames = Sets.newHashSet();
            for(Role role : roles){
                roleIds.add(role.getId());
                roleNames.add(role.getName());
            }
            //放入角色
            session.setAttribute(SysConstant.SESSION_USER_ROLES, roleNames);
            info.setRoles(roleNames);

            List<String> perms = Lists.newArrayList();
            //获取权限
            if(!roleIds.isEmpty()){
                perms = roleService.getPerms(roleIds);
                System.out.println("perms:" + perms);
                Set<String> permSet = new HashSet<String>(perms);
                info.setStringPermissions(permSet);
                session.setAttribute(SysConstant.SESSION_USER_PERMS, permSet);
            }

            //获取菜单
            if(perms.size() > 0){
                List<Menu> menus = menuService.getMenuByPerms(perms);
                System.out.println("menus:" + menus);
                session.setAttribute(SysConstant.SESSION_MENU_KEY, menus);
                Map<Integer, Menu> menuMap = Maps.newHashMap();
                for(Menu menu : menus){
                    menuMap.put(menu.getId(), menu);
                }
                session.setAttribute(SysConstant.SESSION_MENU_MAP, menuMap);
            }
        }

        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute(SysConstant.SESSION_USER);

        if(user != null){
            return new SimpleAuthenticationInfo(
                    user.getUsername(), user.getPassword(), getName());
        }

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 通过表单接收的用户名
        String username = token.getUsername();
        if(username != null && !"".equals(username)){
            user = userService.getUserByUsername(username);

            if(user != null){
                if(user.getUserStatus().equals(User.OFF)){
                    throw new DisabledAccountException("账户被禁用");
//            	if(!user.getUserStatus().equals(User.OFF)){
//                    Exception exception = new DisabledAccountException("账户被禁用");
//                    exception.printStackTrace();
//                    throw new DisabledAccountException("账户被禁用");
                }
                return new SimpleAuthenticationInfo(
                        user.getUsername(), user.getPassword(), getName());
            }
        }
        return null;
    }
}
