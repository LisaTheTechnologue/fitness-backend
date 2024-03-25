package com.ecom.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

}
