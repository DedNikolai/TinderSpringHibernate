package com.tinder.service;

import com.tinder.entity.User;
import com.tinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public List<User> getUsersBySex(String sex) {
        return userRepository.findUsersBySex(sex);
    }
}
