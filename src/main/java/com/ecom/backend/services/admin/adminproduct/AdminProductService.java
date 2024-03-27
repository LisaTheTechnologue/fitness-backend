package com.ecom.backend.services.admin.adminproduct;

import com.ecom.backend.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto add(ProductDto dto) throws IOException;
    List<ProductDto> getAll();
    List<ProductDto> getAllByName(String name);
    boolean delete(Long id);
    ProductDto getById(Long productId);
    ProductDto update(Long productId, ProductDto productDto) throws IOException;
}
