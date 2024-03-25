package com.ecom.backend.controllers.admin;

import com.ecom.backend.dto.CategoryDto;
import com.ecom.backend.entity.Category;
import com.ecom.backend.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.create(categoryDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory (){
        return  ResponseEntity.ok(categoryService.getAll());
    }
}
