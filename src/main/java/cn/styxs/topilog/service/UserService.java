package cn.styxs.topilog.service;

import cn.styxs.topilog.model.User;
import cn.styxs.topilog.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description: 提供用户相关功能
 */
@Service
public class UserService {
    public static final String kTokenAttributeName = "token";

    @Autowired
    UserRepository userRepository;
    Random random = new SecureRandom();

    /*
        获取某用户的盐值
     */
    public String getUserSalt(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            return null;
        if (user.getSalt() == null) {
            user.setSalt(genSalt());
            userRepository.save(user);
        }
        return user.getSalt();
    }

    public static final String kCheckNote = "用户名需要为6~16位 大小写+数字";
    public int checkUsername(String username) {
        if (username.length() < 6 || username.length() > 16) {
            return 1;
        } else if (!username.matches("([0-9]|[a-z]|[A-Z])*")) {
            return 2;
        }
        return 0;
    }

    /*
        判断用户(名)是否已存在
     */
    public boolean isUserExist(String username) {
        User user = userRepository.findUserByUsername(username);
        return user!=null;
    }

    /*
        创建一个用户
        返回值 0-成功 1-用户名不合法 2-用户名已存在 3-其他问题
     */
    public int createUser(String username, String password, String salt) {
        if (checkUsername(username)!=0) {
            return 1;
        } else if (isUserExist(username)) {
            return 2;
        } if (userRepository.save(User.builder().username(username).password(password).salt(salt).build()) == null) {
            return 3;
        }
        return 0;
    }

    /*
        验证用户名-密码是否匹配
        (注意数据库中存储的是加盐后的密码)
     */
    public boolean verifyUser(String username, String passwordWithSalt) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && user.getPassword() != null) {
            if (user.getPassword().equals(passwordWithSalt)) {
                return true;
            }
        }
        return  false;
    }

    private HashMap<String, String> keep_login = new HashMap<>();
    private HashMap<String, String> username_token = new HashMap<>();
    /*
        使指定用户登录
        返回值为token串
     */
    public String keepLogin(String username) {
        // 禁止重复登录
        if (username_token.get(username) != null) {
            keep_login.remove(username_token.get(username));
        }
        String token = UUID.randomUUID().toString();
        keep_login.put(token, username);
        return token;
    }

    /*
        验证token对应的用户是否登录
        返回值为对应的用户名
        无对应用户则返回null
     */
    public String verifyLogin(String token) {
        if (keep_login.containsKey(token)) {
            return keep_login.get(token);
        }
        return null;
    }

    /*
        生成一串盐值
     */
    public String genSalt() {
        byte[] salt = new byte[24];
        random.nextBytes(salt);
        return Base64.encodeBase64URLSafeString(salt);
    }
}
