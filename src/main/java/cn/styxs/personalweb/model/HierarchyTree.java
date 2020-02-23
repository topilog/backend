package cn.styxs.personalweb.model;

import java.util.HashMap;

/**
 * @Author: styxs
 * @CreateTime: 2020/2/21
 * @Description: 路径-规则 基于层次结构的查找树
 */
public class HierarchyTree<T>{
    public class TreeNode<T> {
        String val;
        T extra;
        HashMap<String, TreeNode> childNodeMap = new HashMap<>();
    }
    private TreeNode<T> root = new TreeNode<>();

    /*
        rule: 需要拦截的路径(通配符只能处于最后一层，且只能单独出现)
        例如: 支持 /user/* ； 不支持 /user/a*
        同时 /user 规则不会拦截 /user/login
     */
    public void addRule(String rule, T extra) {
        if (rule.charAt(0) == '/') {
            // 以/开头将导致有一个val为空的节点，统一去除
            rule = rule.substring(1);
        }
        String[] hierarchy = rule.split("/");
        TreeNode<T> curr = root;
        for(String s : hierarchy) {
            switch (containsChildHierarchy(curr, s)) {
                case -1:
                case 1:
                    curr = addChild(curr, s, extra);
                    continue;
                case 0:
                    curr = curr.childNodeMap.get(s);
                    continue;
            }
        }
    }

    /*
        获取某uri是否与给定的规则匹配
     */
    public boolean matchRule(String uri) {
        return findNode(uri) != null;
    }

    public T getExtra(String uri) {
        return findNode(uri).extra;
    }

    private TreeNode<T> findNode(String uri) {
        if (uri.charAt(0) == '/') {
            uri = uri.substring(1);
        }
        String[] hierarchy = uri.split("/");
        TreeNode<T> curr = root;
        for (String s : hierarchy) {
            curr = getChild(curr, s);
            if (curr == null || curr.val.equals("*")) {
                // 无法匹配，返回null
                // *只能出现在末尾，立即返回
                break;
            }
        }
        return curr;
    }


    // -1 表示不匹配 0 表示匹配 1 表示匹配*
    private int containsChildHierarchy(TreeNode<T> node, String str) {
        // 先判断是否有匹配，若无再判断是否有通配符
        if (node.childNodeMap.containsKey(str)) {
            return 0;
        } else if (node.childNodeMap.containsKey("*")) {
            return 1;
        }
        return -1;
    }

    private TreeNode<T> addChild(TreeNode<T> node, String str, T extra) {
        TreeNode<T> newNode = new TreeNode<>();
        newNode.val = str;
        newNode.extra = extra;
        node.childNodeMap.put(str, newNode);
        return newNode;
    }

    private TreeNode<T> getChild(TreeNode<T> node, String str) {
        switch (containsChildHierarchy(node, str)) {
            case 0:
                return node.childNodeMap.get(str);
            case 1:
                // 如果是通过*匹配，需要取*节点而不是str节点
                return node.childNodeMap.get("*");
            default:
                return null;
        }
    }
}
