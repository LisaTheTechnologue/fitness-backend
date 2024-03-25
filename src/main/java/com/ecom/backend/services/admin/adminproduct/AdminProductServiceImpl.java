package com.ecom.backend.services.admin.adminproduct;

import com.ecom.backend.dto.CategoryDto;
import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.entity.Category;
import com.ecom.backend.entity.Product;
import com.ecom.backend.repository.CategoryRepository;
import com.ecom.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto add(ProductDto dto) throws IOException {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImg(dto.getImg().getBytes());
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(Product::getDto).collect(Collectors.toList());
    }
}
