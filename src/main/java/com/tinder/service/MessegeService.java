package com.tinder.service;

import com.tinder.entity.Messege;
import com.tinder.repository.MessegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
public class MessegeService {
    @Autowired
    private MessegeRepository messegeRepository;

    public Messege addMessege(Messege messege) {
        return messegeRepository.saveAndFlush(messege);
    }

    public TreeMap<Long, Messege> getChat(Long whoId, Long whomId) {
        TreeMap<Long, Messege> messeges = new TreeMap<>();
        List<Messege> allMessegesWithUsers = messegeRepository.findAll();
        for (Messege messege : allMessegesWithUsers) {
            if (messege.getWhoMessege() == whoId && messege.getWhomMessege() == whomId) {
                messeges.put(messege.getTime(), messege);
            }

            if (messege.getWhoMessege() == whomId && messege.getWhomMessege() == whoId) {
                messeges.put(messege.getTime(), messege);
            }
        }

        return messeges;
    }
}
