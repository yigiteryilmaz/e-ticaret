package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    public List<ProductResponseDto> findAll();

    public ProductResponseDto findById(Long id);

    public Product findByProduct(Long id);

    public ProductResponseDto save(Long categoryId,Product product);

    public ProductResponseDto update(Long id,Product product);

    public ProductResponseDto delete(Long id);

}
