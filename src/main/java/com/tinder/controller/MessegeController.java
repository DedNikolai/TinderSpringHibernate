package com.tinder.controller;

import com.tinder.entity.Messege;
import com.tinder.entity.User;
import com.tinder.service.MessegeService;
import com.tinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.TreeMap;

@Controller
public class MessegeController {
    @Autowired
    private MessegeService messegeService;
    @Autowired
    private UserService userService;
    private User currentUser;
    private TreeMap<Long, Messege> messegeMap;

    @GetMapping("/messeges/{login}/{id}")
    public String getChatWithLikedUser(Model model, @PathVariable("login") String userLogin, @PathVariable("id") Long friendUserId) {
        currentUser = userService.getUserByLogin(userLogin);
        messegeMap = messegeService.getChat(currentUser.getId(), friendUserId);
        model.addAttribute("messegeMap", messegeMap);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("friend", userService.getUserById(friendUserId));
        return "userschat";
    }

    @PostMapping("/messeges/{login}/{id}")
    public String sendMessege(Model model, @PathVariable("login") String userLogin, @PathVariable("id") Long friendUserId, @RequestParam("messege") String messegeText) {
        Messege newMessege = new Messege();
        newMessege.setWhoMessege(currentUser.getId());
        newMessege.setWhomMessege(friendUserId);
        newMessege.setText(messegeText);
        newMessege.setTime(System.currentTimeMillis());
        messegeService.addMessege(newMessege);
        messegeMap.put(newMessege.getTime(), newMessege);
        model.addAttribute("messegeMap", messegeMap);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("friend", userService.getUserById(friendUserId));
        return "userschat";
    }
}
