package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserDAO {

    List<Role> selectallRoles();

    List<Role> selectRolesByUserId(Long id);

    List<User> userById(Integer id);

    boolean checkExistsPerson(String login);

    User findByLoginUser(String login);

    List<Role> findByUserRole(String login);

    List<User> findAllUser();

    boolean createUser(User user , List<Role> roles)  throws SQLException;

    boolean updateUser(User user,List<Role> roles , Integer id) throws SQLException;

    boolean deleteUser(String login) throws SQLException;

}
