package com.ecommerce.project.controllers;

import com.ecommerce.project.models.Category;
import com.ecommerce.project.payloads.requests.CategoryDTO;
import com.ecommerce.project.payloads.response.CategoryResponse;
import com.ecommerce.project.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/public/categories" , method = RequestMethod.GET)
    public ResponseEntity getAllCategories() {
        try {
             CategoryResponse response = categoryService.getAllCategory();
             return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return new ResponseEntity("Unable to get all categories",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/admin/categories/create" , method = RequestMethod.POST)
    public ResponseEntity createCategory(@RequestBody @Valid CategoryDTO category) {
        try {
            CategoryDTO categoryDTO = categoryService.createCategory(category);
            return ResponseEntity.ok(categoryDTO);
        } catch (ResponseStatusException e) {
            return new ResponseEntity(e.getReason(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/public/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @RequestMapping(value = "/admin/categories/update/{id}" , method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@RequestBody @Valid CategoryDTO categoryDTO , @PathVariable Long id){
        try{

            CategoryDTO categoryUpdated = categoryService.updateCategory(categoryDTO , id);
            return ResponseEntity.ok().body(categoryUpdated);
        }
        catch (ResponseStatusException e){
            return ResponseEntity.badRequest().body(String.format("unable to update category with id %d" , id));
        }
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public ResponseEntity handleEcho(@RequestParam(name="name") String param){
        return ResponseEntity.ok().body(String.format("Hello, %s!" , param));
    }
}
