package com.ecom.backend.services.admin.adminproduct;

import com.ecom.backend.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto add(ProductDto dto) throws IOException;
    List<ProductDto> getAll();
}