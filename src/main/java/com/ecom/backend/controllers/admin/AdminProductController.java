package com.ecom.backend.controllers.admin;

import com.ecom.backend.dto.CategoryDto;
import com.ecom.backend.dto.FAQDto;
import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.entity.Category;
import com.ecom.backend.services.admin.adminproduct.AdminProductService;
import com.ecom.backend.services.admin.faq.FAQService;
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
    private final FAQService faqService;
    @PostMapping("/product")
    public ResponseEntity<ProductDto> add(@ModelAttribute ProductDto dto) throws IOException {
        ProductDto dto1 = adminProductService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts (){
        return ResponseEntity.ok(adminProductService.getAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllBName (@PathVariable String name){
        return ResponseEntity.ok(adminProductService.getAllByName(name));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> delete (@PathVariable Long productId){
        boolean deleted = adminProductService.delete(productId);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> create(@PathVariable Long productId, @RequestBody FAQDto faqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.create(productId, faqDto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long productId){
        ProductDto dto1 = adminProductService.getById(productId);
        if (dto1 != null) {
            return ResponseEntity.ok(dto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> update(@PathVariable Long productId,@ModelAttribute ProductDto dto) throws IOException{
        ProductDto dto1 = adminProductService.update(productId,dto);
        if (dto1 != null) {
            return ResponseEntity.ok(dto1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
