package com.hhly.smartdata.security;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.util.SysConstant;
import com.hhly.smartdata.model.authentication.Menu;
import com.hhly.smartdata.model.authentication.Role;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.MenuService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 用于认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute(SysConstant.SESSION_USER);

        if(user != null){
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 通过表单接收的用户名
        String username = token.getUsername();
        if(username != null && !"".equals(username)){
            try{
                user = userService.getUserByUsername(username);
            }catch(Exception e){
                e.printStackTrace();
            }

            if(user != null){
                if(user.getUserStatus().equals(User.OFF)){
                    throw new DisabledAccountException("账户被禁用");
                }
                return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            }
        }
        return null;
    }

    /**
     * 用于授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute(SysConstant.SESSION_USER);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if(user != null){
            Set<String> roleNames = (Set<String>) session.getAttribute(SysConstant.SESSION_USER_ROLES);
            Set<String> permSet = (Set<String>) session.getAttribute(SysConstant.SESSION_USER_PERMS);
            authorizationInfo.setRoles(roleNames);
            authorizationInfo.setStringPermissions(permSet);
        }else{
            if(principalCollection == null){
                throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
            }
            String username = (String) getAvailablePrincipal(principalCollection);
            try{
                user = userService.getUserByUsername(username);
            }catch(Exception e){
                e.printStackTrace();
            }
            if(user == null) return null;
            //放入用户
            session.setAttribute(SysConstant.SESSION_USER, user);

            //获取角色
            List<Role> roles = null;
            try{
                roles = roleService.getRolesByUserId(user.getUserId());
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("roles:" + roles);
            List<Integer> roleIds = Lists.newArrayList();
            Set<String> roleNames = Sets.newHashSet();
            for(Role role : roles){
                roleIds.add(role.getId());
                roleNames.add(role.getName());
            }
            //放入角色
            session.setAttribute(SysConstant.SESSION_USER_ROLES, roleNames);
            authorizationInfo.setRoles(roleNames);

            List<String> perms = Lists.newArrayList();
            //获取权限
            if(!roleIds.isEmpty()){
                try{
                    perms = roleService.getPerms(roleIds);
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("perms:" + perms);
                Set<String> permSet = new HashSet<>(perms);

                authorizationInfo.setStringPermissions(permSet);
                session.setAttribute(SysConstant.SESSION_USER_PERMS, permSet);
            }

            //获取菜单
            if(perms.size() > 0){
                List<Menu> menus = null;
                try{
                    menus = menuService.getMenuByPerms(perms);
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("menus:" + menus);
                session.setAttribute(SysConstant.SESSION_MENU_KEY, menus);
                Map<Integer, Menu> menuMap = Maps.newHashMap();
                for(Menu menu : menus){
                    menuMap.put(menu.getId(), menu);
                }
                session.setAttribute(SysConstant.SESSION_MENU_MAP, menuMap);
            }
        }

        return authorizationInfo;
    }
}
