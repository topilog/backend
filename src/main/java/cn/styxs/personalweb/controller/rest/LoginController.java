package cn.styxs.personalweb.controller.rest;

import cn.styxs.personalweb.controller.response.LoginResponse;
import cn.styxs.personalweb.controller.response.SaltResponse;
import cn.styxs.personalweb.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/salt", method = {RequestMethod.GET})
    @ApiOperation(value = "请求用户的salt值", notes = "不带参数为请求公共salt值")
    @ApiImplicitParam(name = "username", value = "用户名")
    public SaltResponse getSalt(@RequestParam(value = "username", defaultValue = "")String username) {
        SaltResponse saltResponse = new SaltResponse();
        if ("".equals(username)) {
            saltResponse.setSalt(userService.genSalt());
            saltResponse.succeed();
        } else {
            String userSalt = userService.getUserSalt(username);
            if (userSalt == null) {
                saltResponse.failed("can't find user by this username", 1);
            } else {
                saltResponse.setSalt(userSalt);
                saltResponse.succeed();
            }
        }
        return saltResponse;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ApiOperation(value = "注册", notes = "口令先获取salt值加密，注册成功后将自动登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "加盐后口令"),
            @ApiImplicitParam(name = "salt", value = "salt值")
    })
    public LoginResponse register(@RequestParam(value = "username")String username,
                                  @RequestParam(value = "password")String password,
                                  @RequestParam(value = "salt")String salt, HttpServletRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        int code = userService.createUser(username, password, salt);
        if (code == 0) {
            String token = userService.keepLogin(username);
            request.getSession().setAttribute(UserService.kTokenAttributeName, token);
            loginResponse.setToken(token);
            loginResponse.succeed();
        } else {
            loginResponse.failed("failed", code);
        }
        return loginResponse;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ApiOperation(value = "登录", notes = "口令先获取salt值加密")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "加盐后口令")
    })
    public LoginResponse login(@RequestParam(value = "username")String username,
                               @RequestParam(value = "password")String password, HttpServletRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        if (userService.verifyUser(username, password)) {
            String token = userService.keepLogin(username);
            request.getSession().setAttribute(UserService.kTokenAttributeName, token);
            loginResponse.setToken(token);
            loginResponse.succeed();
        } else {
            loginResponse.failed("can't verify user", 1);
        }
        return loginResponse;
    }
}
