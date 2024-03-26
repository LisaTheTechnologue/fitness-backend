package com.ecom.backend.services.customer;

import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.entity.Product;
import com.ecom.backend.repository.CategoryRepository;
import com.ecom.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> getAllByName(String name) {
        return productRepository.findAllByNameContaining(name).stream().map(Product::getDto).collect(Collectors.toList());
    }
}
