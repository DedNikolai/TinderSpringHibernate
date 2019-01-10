package com.tinder.controller;

import com.tinder.entity.Like;
import com.tinder.entity.User;
import com.tinder.service.LikeService;
import com.tinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    private int count = 0;
    private  List<User> userList = new ArrayList<>();
    private User user;

    @GetMapping("/user/{login}")
    public ModelAndView getUserById(@PathVariable("login") String userLogin) {
        ModelAndView model = new ModelAndView();
        user = userService.getUserByLogin(userLogin);
        String sex;
        if (user.getSex().equals("man")) {
            sex = "woman";
        } else {
            sex = "man";
        }
        userList = userService.getUsersBySex(sex);
        model.getModel().put("user", user);
        model.getModel().put("current", userList.get(0));
        model.setViewName("userprofile");
        return model;
    }

    @PostMapping(path= "/user/{login}", params = {"like=like"})
     public String likeUser(Model model, @RequestParam(value = "whomLikeId", required = false) Long whomLikedId) {
        Like like = new Like();
        like.setWhoLike(user.getId());
        like.setWhomLike(whomLikedId);
        likeService.addLike(like);
        count++;
        if (count == userList.size()) {
            count =0;
        }
        model.addAttribute("user", user);
        model.addAttribute("current", userList.get(count));
        return "userprofile";
    }

    @PostMapping(path = "/user/{login}", params = {"like=dislike"})
    public String dislikeUser(Model model) {
        count++;
        if (count == userList.size()) {
            count = 0;
        }
        model.addAttribute("user", user);
        model.addAttribute("current", userList.get(count));
        return "userprofile";
    }
}
