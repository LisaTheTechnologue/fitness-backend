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
import java.util.Optional;
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

    public List<ProductDto> getAllByName(String name) {
        return productRepository.findAllByNameContaining(name).stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean delete(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductDto getById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getDto();
        } else {
            return null;
        }
    }

    public ProductDto update(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalProduct.isPresent() && optionalCategory.isPresent() ) {
            Product product = optionalProduct.get();

            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(optionalCategory.get());

            if (productDto.getImg() != null) {
                product.setImg(productDto.getImg().getBytes());
            }
            return productRepository.save(product).getDto();
        } else {
            return null;
        }
    }
}
