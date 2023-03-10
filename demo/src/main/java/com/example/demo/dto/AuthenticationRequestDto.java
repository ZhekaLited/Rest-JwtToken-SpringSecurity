package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class AuthenticationRequestDto {
   private Long id;
   private String login;
   private String password;

   private List<Role> roles;
}
