package cn.styxs.topilog.model;

/**
 * @Author: styxs
 * @CreateTime: 2020/3/6
 * @Description:
 */
public class ErrorCode {
    public class BaseLayerCode {
        public static final int kNeedLogin = 1;         // 需要登录
        public static final int kNeedPermission = 2;    // 需要权限
    }

    public static final int kIllegalArgument = 100;   // 不合法的参数
    /////////////   以下为业务层错误码
    public class Article {
        public static final int kArticleIdError = 1;    // 文章Id错误
    }

    public class User {
        public static final int kInvalidUsername = 1;           // 注册：不合法的用户名
        public static final int kUsernameHasExisted = 2;        // 注册：用户名已存在
        public static final int kRegisterError = 3;             // 注册时的其他问题

        public static final int kUsernameNotExisted = 4;        // 用户名不存在
        public static final int kLoginError = 5;                // 登录错误
    }
}
