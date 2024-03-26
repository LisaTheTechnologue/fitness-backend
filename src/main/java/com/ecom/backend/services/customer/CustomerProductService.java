package com.ecom.backend.services.customer;

import com.ecom.backend.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    List<ProductDto> getAll();
    List<ProductDto> getAllByName(String name);
}
