package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.LoginDaoImpl;
import com.example.demo.repository.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    LoginDaoImpl loginDaoImpl;

    @Override
    public List<Role> selectRolesByUserId(Long id) {
        return userDAO.selectRolesByUserId(id);
    }

    @Override
    public List<Role> selectallRoles() {
        return userDAO.selectallRoles();
    }

    @Override
    public List<User> userById(Integer id) {
        return userDAO.userById(id);
    }


    @Override
    public User findByUserAuth(String username) throws ClassNotFoundException {
        return loginDaoImpl.findByUserAuth(username);
    }

    @Override
    public List<User> findAllUser() {
        return (userDAO.findAllUser());
    }

    @Override
    public boolean createUser(User user, List<Role> roles) throws SQLException {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return (userDAO.createUser(user, roles));
    }

    @Override
    public boolean updateUser(User user, List<Role> roles, Integer id) throws SQLException {
        return (userDAO.updateUser(user, roles, id));
    }

    @Override
    public boolean deleteUser(String login) throws SQLException {
        return (userDAO.deleteUser(login));
    }

    @Override
    public List<Role> findByRolesAuth(String login) throws SQLException {
        return (loginDaoImpl.findByRolesAuth(login));
    }
}


//    @Override
//    public boolean checkExistsPerson(String login) {
//        return (userDAO.checkExistsPerson(login));
//    }

//    @Override
//    public User findByLoginUser(String login) {
//        return (userDAO.findByLoginUser(login));
//    }

//    @Override
//    public List<Role> findByUserRole(String login) {
//        return (userDAO.findByUserRole(login));
//    }
