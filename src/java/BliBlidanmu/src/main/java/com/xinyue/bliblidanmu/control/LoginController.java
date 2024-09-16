package com.xinyue.bliblidanmu.control;

import com.xinyue.bliblidanmu.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "http://192.168.1.12:8080")
public class LoginController {

    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public String login(@RequestBody User user){
        System.out.println("用户名"+user.getName());
        System.out.println("密码"+user.getPassword());

        return "redirect:/index";
    }
}

