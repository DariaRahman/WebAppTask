package com.example.webapp.controller;

import com.example.webapp.domain.Role;
import com.example.webapp.domain.User;
import com.example.webapp.repos.UserRepo;
import com.example.webapp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UsersService usersService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }

//    @GetMapping("{user}")
//    public String userEditForm(@PathVariable User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("roles", Role.values());
//
//        return "userEdit";
//    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";

    }

    @PostMapping("upd")
    String usersToolbar(@Nullable @RequestParam("checkboxName") String checkboxValue[],
                        @RequestParam(name = "buttonType") String buttonType,
                        @RequestParam Map<String, String> form
    ){
        System.out.println(form);
        if (form.size() > 2)
        switch (buttonType){
            case ("delete"):
                    for (String key : form.keySet()) {
                        if (form.get(key).equals(key))
                        usersService.deleteUserById(Long.parseLong(key));
                }
                break;
            case ("unlock"):
                    for (String key : form.keySet()) {
                        if (form.get(key).equals(key))
                            usersService.unlockUserById(Long.parseLong(key));
                    }
                break;
            case ("lock"):
                    for (String key : form.keySet()) {
                        if (form.get(key).equals(key))
                        usersService.lockUserById(Long.parseLong(key));

                }
                break;
        }
        return "redirect:/user";
    }

}




