package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
  
    @Autowired
    private PasswordEncoder passwordEncoder; //Нужно сделать Autowired иначе работать не будет

    @GetMapping("/users/list")
    public List<User> listUser() {
        return userService.findAllUser();
    }

    @GetMapping("/users/listRole")
    public List<Role> selectallRoles() {
        return userService.selectallRoles();
    }

    @GetMapping("/users/userById")
    public User selectUserById(String id) {
        if (id != null) {
            List<User> users = userService.userById(Integer.parseInt(id));
            if (users != null && users.size() > 0) {
                User user = users.get(0);
                user.roles = userService.selectRolesByUserId(Long.parseLong(id));
                return user;
            }
        }
        return null;
    }

    @PostMapping("/users/add")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean postCreate(@RequestBody User user) throws SQLException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  //Хэширование пароля при добавлении!!!
        user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
        return userService.createUser(user, user.roles);
    }

    @PutMapping("/users/edit")
    public boolean postUpdate(@RequestBody User user, @RequestParam("id") Integer id) throws SQLException {
        user.setRoles(user.roles);
        user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
        return userService.updateUser(user, user.roles, id);
    }


    @DeleteMapping("/users/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestParam("login") String login) throws SQLException {
        return userService.deleteUser(login);
    }
}
