package com.tinder.controller;

import com.tinder.entity.User;
import com.tinder.service.LikeService;
import com.tinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LikedController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;

    @PostMapping("/likedlist/{login}")
    public String getPageWithListOfLikedUsers(Model model, @PathVariable("login") String userLogin) {
        Long currentUserId = userService.getUserByLogin(userLogin).getId();
        List<Long> listIdOfLikedUsers = likeService.getLikedUsers(currentUserId);
        List<User> users = new ArrayList<>();
        for (Long id : listIdOfLikedUsers) {
            users.add(userService.getUserById(id));
        }
        model.addAttribute("currentLogin", userLogin);
        model.addAttribute("users", users);
        return "likedlist";
    }
}
