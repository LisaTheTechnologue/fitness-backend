package com.ecom.backend.dto;

import com.ecom.backend.enums.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
