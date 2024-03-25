package com.ecom.backend.services.admin.category;

import com.ecom.backend.dto.CategoryDto;
import com.ecom.backend.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDto categoryDto);
    List<Category> getAll();
}
