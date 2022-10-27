package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author damu
 * @date 2022/10/19 20:46
 */
@Api(tags = "登录管理")
@RequestMapping("/admin/hosp")
@CrossOrigin // 跨域
@RestController
public class IndexController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin_token");
    }

    @ApiOperation("获取信息")
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","admin")
                    .data("introduction","I am a super administrator")
                    .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                    .data("name","大大怪将军");
    }


}
