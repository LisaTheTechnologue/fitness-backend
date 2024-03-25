package com.ecom.backend.entity;

import com.ecom.backend.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @Lob
//  @Column(columnDefinition = "bytea")
  private byte[] img;

  private UserRole role;

}
