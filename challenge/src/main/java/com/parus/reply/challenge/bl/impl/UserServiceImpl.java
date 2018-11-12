package com.parus.reply.challenge.bl.impl;

import com.parus.reply.challenge.bl.UserService;
import com.parus.reply.challenge.dl.UserRepository;
import com.parus.reply.challenge.dl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public List<User> listAllUsers() {
        List<User> resultList = new LinkedList<>();
        Iterable<User> iterable = userRepo.findAll();
        // add all entities to result list
        iterable.forEach(resultList::add);
        return resultList;
    }

    @Override
    public void removeUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public User updateUserDetails(User userToUpdate) {
        return userRepo.save(userToUpdate);
    }
}
