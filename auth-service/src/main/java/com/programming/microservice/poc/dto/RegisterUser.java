package com.programming.microservice.poc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
  @NotBlank(message = "username can't be blank or null")
  @Size(min = 3)
  private String username;

  @NotBlank(message = "password can't be null or blank")
  private String password;

  private String firstName;

  private String lastName;

  @Email(message = "must be a proper email format")
  private String email;
}
