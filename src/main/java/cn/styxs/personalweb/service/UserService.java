package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.User;
import cn.styxs.personalweb.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
    public static final String kTokenAttributeName = "token";

    @Autowired
    UserRepository userRepository;
    Random random = new SecureRandom();

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

    public boolean isUserExist(String username) {
        User user = userRepository.findUserByUsername(username);
        return user!=null;
    }

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
    public String keepLogin(String username) {
        // 禁止重复登录
        if (username_token.get(username) != null) {
            keep_login.remove(username_token.get(username));
        }
        String token = UUID.randomUUID().toString();
        keep_login.put(token, username);
        return token;
    }

    public String verifyLogin(String token) {
        if (keep_login.containsKey(token)) {
            return keep_login.get(token);
        }
        return null;
    }

    public String genSalt() {
        byte[] salt = new byte[24];
        random.nextBytes(salt);
        return Base64.encodeBase64URLSafeString(salt);
    }
}
