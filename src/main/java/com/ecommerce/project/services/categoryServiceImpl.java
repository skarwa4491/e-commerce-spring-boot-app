package com.ecommerce.project.services;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.models.Category;
import com.ecommerce.project.payloads.requests.CategoryDTO;
import com.ecommerce.project.payloads.response.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryDTO categoryDTO;
    @Override
    public CategoryResponse getAllCategory() {
        System.out.println(categoryDTO);
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = categories.stream().map(category ->{
            return modelMapper.map(category, CategoryDTO.class);
        }).collect(Collectors.toList());
        CategoryResponse response = new CategoryResponse();
        response.setContent(categoriesDTO);
        return response;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category catergoryModel = modelMapper.map(category , Category.class);
        Optional<Category> doestAlreadyExist = categoryRepository.findByCategoryName(catergoryModel.getCategoryName());
        if (doestAlreadyExist.isPresent()) {
            throw new APIException(String.format("category with name %s already exist", category.getCategoryName()));
        }
        Category savedCategory = categoryRepository.save(catergoryModel);
        return modelMapper.map(savedCategory , CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> {
            //return new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found to delete");
            return new ResourceNotFoundException("Category", "id", id);
        });
        categoryRepository.delete(existingCategory);
        CategoryDTO categoryDTO = modelMapper.map(existingCategory , CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) throws ResourceNotFoundException {
        Category category = modelMapper.map(categoryDTO , Category.class);
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> {
            //return new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found");
            return new ResourceNotFoundException("Category", "id", id);
        });

        existingCategory.setCategoryName(category.getCategoryName());
        Category updatedCategory =  categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory , CategoryDTO.class);
    }


}
