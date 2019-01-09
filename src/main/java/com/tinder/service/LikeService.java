package com.tinder.service;

import com.tinder.entity.Like;
import com.tinder.entity.User;
import com.tinder.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public void addLike(Like like) {
        likeRepository.saveAndFlush(like);
    }

    public List<Long> getLikedUsers(Long id) {
        List<Long> likedUsers = new ArrayList<>();
        List<Like> likes = likeRepository.findAll();
        for (Like like : likes) {
            if (like.getWhoLike() == id) {
                likedUsers.add(like.getWhomLike());
            }
        }

        return likedUsers;
    }
}
