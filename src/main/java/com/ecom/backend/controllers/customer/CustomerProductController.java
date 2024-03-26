package com.ecom.backend.controllers.customer;

import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.services.admin.adminproduct.AdminProductService;
import com.ecom.backend.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts (){
        return ResponseEntity.ok(customerProductService.getAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllBName (@PathVariable String name){
        return ResponseEntity.ok(customerProductService.getAllByName(name));
    }
}
