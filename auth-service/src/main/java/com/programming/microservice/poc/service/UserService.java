package com.programming.microservice.poc.service;

import com.programming.microservice.poc.dto.LoginUser;
import com.programming.microservice.poc.dto.RegisterUser;
import com.programming.microservice.poc.mapper.MapperService;
import com.programming.microservice.poc.model.Role;
import com.programming.microservice.poc.model.User;
import com.programming.microservice.poc.repository.RolesRepository;
import com.programming.microservice.poc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

  private final MapperService mapperService;
  private final UserRepository userRepository;
  private final RolesRepository rolesRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Transactional
  public void registerUser(RegisterUser registerUser) {
    if(userRepository.existsByUsername(registerUser.getUsername())){
      throw new RuntimeException("Username already exists");
    }
    if(userRepository.existsByEmail(registerUser.getEmail())){
      throw new RuntimeException("Email already exists");
    }
    Role role = rolesRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));
    User user=mapperService.mapToUser(registerUser);
    //add roles to the user
    user.setRoles(Set.of(role));
    userRepository.save(user);
  }

  public String loginUser(LoginUser loginUser) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
    );
    String jwtToken=jwtService.generateToken(loginUser.getUsername());
    log.info("JWT Token generated for user {}: {}", loginUser.getUsername(), jwtToken);
    return jwtToken;
  }
}
