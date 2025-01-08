package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryRequestDto;
import com.workintech.ecommerce.dto.CategoryResponseDto;
import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.ApiException;
import com.workintech.ecommerce.repository.CategoryRepository;
import com.workintech.ecommerce.util.ConvertCategoryAndProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;
@Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        List<Category> categories=categoryRepository.findAll();
        return ConvertCategoryAndProduct.converCategoryList(categories);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Optional<Category> categoryOptional=categoryRepository.findById(id);
        if (!categoryOptional.isPresent()){
            throw new ApiException("bu id'de category yok!", HttpStatus.BAD_REQUEST);
        }
        Category category=categoryOptional.get();
        return ConvertCategoryAndProduct.converCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto update(Category category) {
        findByCategory(category.getId());
        categoryRepository.save(category);

        return ConvertCategoryAndProduct.converCategoryResponseDto(category);
    }

    @Override
    public Category findByCategory(Long id) {
        Optional<Category> categoryOptional=categoryRepository.findById(id);
        if (!categoryOptional.isPresent()){
            throw new ApiException("bu id'de category yok!", HttpStatus.BAD_REQUEST);
        }
        return categoryOptional.get();
    }

    @Override
    public CategoryResponseDto save(Category categoryRequest) {

        Category savedCategory= categoryRepository.save(categoryRequest);

        return ConvertCategoryAndProduct.converCategoryResponseDto(savedCategory);
    }

    @Override
    public CategoryResponseDto delete(Long id) {
        Category category=findByCategory(id);
        categoryRepository.delete(category);
        return ConvertCategoryAndProduct.converCategoryResponseDto(category);
    }
}
