package com.programming.microservice.poc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
  @NotBlank(message = "username can't be blank or null")
  @Size(min = 3,message = "username must be of at least 3 characters")
  private String username;

  @NotBlank(message = "password can't be null or blank")
  private String password;
}
