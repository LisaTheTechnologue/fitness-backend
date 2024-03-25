package com.ecom.backend.controllers.admin;

import com.ecom.backend.dto.CategoryDto;
import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.entity.Category;
import com.ecom.backend.services.admin.adminproduct.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> add(@RequestBody ProductDto dto) throws IOException {
        ProductDto dto1 = adminProductService.add(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(dto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts (){
        return  ResponseEntity.ok(adminProductService.getAll());
    }
}
