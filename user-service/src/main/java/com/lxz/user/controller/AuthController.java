package com.lxz.user.controller;

import com.lxz.user.service.AuthService;
import com.lxz.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 登录授权，生成JWT
     * @param email
     * @param pwd
     * @return
     */
    @PostMapping("/auth")
    public ResultVO loginAuth(@RequestParam String email,
                                    @RequestParam String pwd){
        ResultVO resultVO = authService.loginAuth(email, pwd);
        return resultVO;
    }

    /**
     * 刷新JWT
     * @param refreshToken
     * @return
     */
    @GetMapping("/token/refresh")
    public Map<String,Object> refreshToken(@RequestParam String refreshToken){
        Map<String, Object> stringObjectMap = authService.refreshToken(refreshToken);
        return stringObjectMap;
    }


    /**
     * 登录授权，生成JWT
     * @param email
     * @return
     */
    @PostMapping("/updatePwdAuth")
    public ResultVO updatePwdAuth(@RequestParam String email){
        ResultVO resultVO = authService.updatePwdAuth(email);
        return resultVO;
    }




}