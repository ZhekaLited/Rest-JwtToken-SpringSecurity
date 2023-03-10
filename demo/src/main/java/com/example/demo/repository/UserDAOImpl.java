package com.example.demo.repository;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Role> selectallRoles() {
        return userMapper.selectallRoles();
    }

    @Override
    public List<Role> selectRolesByUserId(Long id) {
        return userMapper.selectRolesByUserId(id);
    }

    @Override
    public List<User> userById(Integer id) {
        return userMapper.userById(id);
    }

    @Override
    public boolean checkExistsPerson(String login) {
        return userMapper.checkExistsPerson(login);
    }

    @Override
    public User findByLoginUser(String login) {
        return userMapper.userByLogin(login);
    }

    @Override
    public List<Role> findByUserRole(String login) {
        List<Role> rolesList = userMapper.getAllRole(login);
        return rolesList;
    }

    @Override
    public List<User> findAllUser() {
        List<User> lstUser = userMapper.selectAllUsers();
        for (User user : lstUser) {
            String userLogin = user.getLogin();
            List<Role> roles = userMapper.selectAllRoles(userLogin);

            user.setRoles(roles);
        }
        return lstUser;
    }

    @Override
    public boolean createUser(User user, List<Role> roles) throws SQLException {
        userMapper.insertUser(user);

        for (Role role : roles) {
            userMapper.insertRole(user.getLogin(), role.getName());
        }
        return true;
    }

    @Override
    public boolean updateUser(User user, List<Role> roles , Integer id) throws SQLException {
        boolean rowUpdated = false;

        try {
            if ((checkExistsPerson(user.getLogin()) && user.getLogin().equals(id)) ||
                    (!userMapper.checkExistsPerson(user.getLogin()) && !user.getLogin().equals(id))) {

                rowUpdated = userMapper.userUpdateEdit(user.getLogin(), user.getPassword(), user.getName(),
                        new SimpleDateFormat("dd.MM.yyyy").parse(user.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
                        user.getAge(), user.getSalary(), id);
            }

            userMapper.deleteRoleEdit(user.getLogin());

            if (user.getRoles() != null) {
                for (Role role : user.getRoles()) {
                    userMapper.insertRoleEdit(user.getLogin(), role.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rowUpdated;
    }

    @Override
    public boolean deleteUser(String login) throws SQLException {
        return userMapper.deleteUser(login);
    }


}
