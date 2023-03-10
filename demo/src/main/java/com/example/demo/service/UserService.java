package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {

    List<Role> selectRolesByUserId(Long id);

    List<Role> selectallRoles();

    List<User> userById(Integer id);

    User findByUserAuth(String username) throws ClassNotFoundException;

    List<User> findAllUser();

    boolean createUser(User user, List<Role> roles) throws SQLException;

    boolean updateUser(User user, List<Role> roles, Integer id) throws SQLException;

    boolean deleteUser(String login) throws SQLException;

    List<Role> findByRolesAuth(String login) throws SQLException;
}


//    boolean checkExistsPerson(String login);
//    User findByLoginUser(String login);
//    List<Role> findByUserRole(String login);
