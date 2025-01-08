package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryRequestDto;
import com.workintech.ecommerce.dto.CategoryResponseDto;
import com.workintech.ecommerce.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService  {

    public List<CategoryResponseDto> findAll();
    public CategoryResponseDto findById(Long id);

    public CategoryResponseDto update(Category category);
    public Category findByCategory(Long id);
    public CategoryResponseDto save(Category category);

    public CategoryResponseDto delete(Long id);
}
