package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.HierarchyTree;
import cn.styxs.personalweb.model.Permission;
import org.springframework.stereotype.Service;


/**
 * @Author: styxs
 * @CreateTime: 2020/2/20
 * @Description: 封装uri控制服务
 */
@Service
public class URIAccessService {

    HierarchyTree<Object> loginInterceptedTree = new HierarchyTree<>();
    HierarchyTree<String> permissionInterceptedTree = new HierarchyTree<>();

    // 判断一个URI是否可以以非登录状态访问
    public boolean canAccessWithoutLogin(String uri) {
        return !loginInterceptedTree.matchRule(uri);
    }

    // 判断一个URI是否可以以无权限状态访问
    public boolean canAccessWithoutPermission(String uri) {
        return !permissionInterceptedTree.matchRule(uri);
    }

    // 获取一个URI所需的权限名
    public String getPermissionWithURI(String uri) {
        if (permissionInterceptedTree.matchRule(uri)) {
            return permissionInterceptedTree.getExtra(uri);
        } else return null;
    }

    // 为URI添加一个登录限制规则
    public void addRuleForLogin(String uri) {
        loginInterceptedTree.addRule(uri, null);
    }

    // 为URI添加一个权限限制规则
    public void addRuleForPermission(String uri, String permissionName) {
        if (!loginInterceptedTree.matchRule(uri)) {
            // 拦截器层要求权限限制必须先限制登录
            loginInterceptedTree.addRule(uri, null);
        }
        permissionInterceptedTree.addRule(uri, permissionName);
    }
}
