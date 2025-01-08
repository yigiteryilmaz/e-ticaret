package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.CategoryRequestDto;
import com.workintech.ecommerce.dto.CategoryResponseDto;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
@Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<CategoryResponseDto> findAll(){
    return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable long id){
    return categoryService.findById(id);
    }

    @PostMapping("/")
    public CategoryResponseDto save(@Valid @RequestBody Category category){
    CategoryRequestDto categoryRequestDto=new CategoryRequestDto(category.getName(), category.getDescription(), category.getProducts());
    return categoryService.save(category);
    }

    @PutMapping("/")
    public CategoryResponseDto update(@RequestBody Category category){
    return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public CategoryResponseDto delete(@PathVariable long id){
    return categoryService.delete(id);
    }


}
