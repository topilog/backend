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

    public boolean canAccessWithoutLogin(String uri) {
        return !loginInterceptedTree.matchRule(uri);
    }

    public boolean canAccessWithPermission(String uri) {
        return !permissionInterceptedTree.matchRule(uri);
    }

    public String getPermissionWithURI(String uri) {
        if (permissionInterceptedTree.matchRule(uri)) {
            return permissionInterceptedTree.getExtra(uri);
        } else return null;
    }

    public void addRuleForLogin(String uri) {
        loginInterceptedTree.addRule(uri, null);
    }

    public void addRuleForPermission(String uri, String permissionName) {
        permissionInterceptedTree.addRule(uri, permissionName);
    }
}
