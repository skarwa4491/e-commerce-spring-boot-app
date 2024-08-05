package com.ecommerce.project.services;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.models.Category;
import com.ecommerce.project.payloads.requests.CategoryDTO;
import com.ecommerce.project.payloads.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategory();
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO category , Long id);

}
