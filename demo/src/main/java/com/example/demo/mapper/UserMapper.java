package com.example.demo.mapper;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {

    User getAllLogin(@Param("username") String username);

    List<User> userById(@Param("id") Integer id);

    List<Role> getAllRole(@Param("login") String login);

    List<Role> selectallRoles();

    List<User> selectAllUsers();

    void insertUser(User user);

    void insertRole(@Param("login") String login, @Param("roleName") String roleName);

    List<Role> selectAllRoles(@Param("login") String login);

    List<Role> selectRolesByUserId(@Param("id") Long id);

    boolean deleteUser(String login);

    boolean checkExistsPerson(@Param("login") String login);

    boolean userUpdateEdit(@Param("login") String login, @Param("password") String password, @Param("name") String name,
                           @Param("birthday") Date birthday, @Param("age") int age, @Param("salary") int salary, @Param("id") Integer id);

    void deleteRoleEdit(@Param("login") String login);

    void insertRoleEdit(@Param("login") String login, @Param("name") String name);

    User userByLogin(@Param("login") String login);

}
