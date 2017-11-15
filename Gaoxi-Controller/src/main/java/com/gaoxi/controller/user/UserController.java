package com.gaoxi.controller.user;

import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Permission;
import com.gaoxi.entity.user.MenuEntity;
import com.gaoxi.entity.user.PermissionEntity;
import com.gaoxi.entity.user.RoleEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.gaoxi.rsp.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:26
 */
@RestController
public interface UserController {

    /**
     * 登录
     * @param loginReq 登录请求参数
     * @param httpRsp HTTP响应
     * @return 登录是否成功
     */
    @GetMapping("/login")
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp);

    /**
     * 注册
     * @param registerReq 注册请求
     * @param httpRsp HTTP响应
     * @return 注册是否成功
     */
    @PostMapping("/register")
    // TODO not test
    public Result register(RegisterReq registerReq, HttpServletResponse httpRsp);


    /**
     * 查询用户信息
     * @param userQueryReq 用户查询请求
     * @return 用户查询结果
     */
    @GetMapping("/user")
    @Login
    @Permission("user:query")
    public Result<List<UserEntity>> findUsers(UserQueryReq userQueryReq);

    /**
     * 批量更新用户状态
     * @param userStateReqs 更新用户状态的请求列表
     * @return 更新是否成功
     */
    @PutMapping("/userState")
    @Login
    @Permission("userState:update")
    // TODO not test
    public Result batchUpdateUserState(BatchReq<UserStateReq> userStateReqs);

    /**
     * 创建管理员
     * @param adminCreateReq 管理员创建请求
     * @return 创建是否成功
     */
    @PostMapping("/admin")
    @Login
    @Permission("admin:create")
    public Result createAdminUser(AdminCreateReq adminCreateReq);

    /**
     * 查询所有角色
     * @return 返回所有角色
     */
    @GetMapping("/role")
    @Login
    @Permission("role:query")
    public Result<List<RoleEntity>> findRoles();

    // TODO 添加创建角色

    /**
     * 删除指定角色
     * @param roleId 角色ID
     * @return 是否删除成功
     */
    @DeleteMapping("/role")
    @Login
    @Permission("role:delete")
    public Result deleteRole(String roleId);

    /**
     * 修改角色对应的菜单
     * @param roleMenuReq 角色-菜单的修改请求
     * @return 是否修改成功
     */
    @PutMapping("/menuOfRole")
    @Login
    @Permission("menuOfRole:update")
    public Result updateMenuOfRole(RoleMenuReq roleMenuReq);

    /**
     * 修改角色对应的权限
     * @param rolePermissionReq 角色-权限的修改请求
     * @return 是否修改成功
     */
    @PutMapping("/permissionOfRole")
    @Login
    @Permission("permissionOfRole:update")
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq);

    /**
     * 查询所有的权限
     * @return 所有的权限列表
     */
    @GetMapping("permission")
    @Login
    @Permission("permission:query")
    public Result<List<PermissionEntity>> findPermissions();

    /**
     * 查询所有的菜单列表
     * @return 所有的菜单列表
     */
    @GetMapping("/menu")
    @Login
    @Permission("menu:query")
    public Result<List<MenuEntity>> findMenus();

}
