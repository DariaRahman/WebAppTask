package com.example.webapp.service;

import com.example.webapp.domain.User;
import com.example.webapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UsersService {

    @Autowired
    UserRepo userRepo;


    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id){
        User user = userRepo.getById(id);
        return user;
    }

    public boolean deleteUser(Long id) {
        if (userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }


    public void updateLastActivity(User user){
        user.setLastActivity(new Date());
        //userRepo.deleteById(user.getId());
        userRepo.save(user);
    }

    public void lockUserById(Long id){
        User newUser = userRepo.getById(id);
        //userRepo.deleteById(id);
        newUser.setEnabled(false);
        newUser.setActive(false);
        userRepo.save(newUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (newUser.getUsername().equals(auth.getName())) {
            auth.setAuthenticated(false);
        }
    }

    public void deleteUserById(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserById(id);
        deleteUser(id);
        if (user.getUsername().equals(auth.getName())) {
            auth.setAuthenticated(false);
        }
    }

    public void unlockUserById(Long id){
        User newUser = userRepo.getById(id);
        //userRepo.deleteById(id);
        newUser.setEnabled(true);
        newUser.setActive(true);
        userRepo.save(newUser);
    }


}
