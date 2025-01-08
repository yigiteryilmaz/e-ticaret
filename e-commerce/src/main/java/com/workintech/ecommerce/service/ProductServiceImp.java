package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.ApiException;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.util.ConvertCategoryAndProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImp implements ProductService{

    private ProductRepository productRepository;
    private CategoryService categoryService;
@Autowired
    public ProductServiceImp(ProductRepository productRepository, @Lazy CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> products=productRepository.findAll();
        return ConvertCategoryAndProduct.convertProductList(products);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Optional<Product> productOptional=productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new ApiException("bu id'de product ürün bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        return ConvertCategoryAndProduct.convertProductResponseDto(productOptional.get());
    }

    @Override
    public Product findByProduct(Long id) {
        Optional<Product> productOptional=productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new ApiException("bu id'de product ürün bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        return productOptional.get();
    }

    @Override
    public ProductResponseDto save(Long categoryId, Product product) {
         Category category= categoryService.findByCategory(categoryId);

         category.addProduct(product);
         product.setCategory(category);

         Product savedProduct=productRepository.save(product);
         return ConvertCategoryAndProduct.convertProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto update(Long id,Product productRequest) {
          Category category= categoryService.findByCategory(id);
       Product productSaved=findByProduct(productRequest.getId());
       productSaved.setCategory(category);
       category.addProduct(productSaved);
        productRepository.save(productSaved);

        return ConvertCategoryAndProduct.convertProductResponseDto(productRequest);
    }

    @Override
    public ProductResponseDto delete(Long id) {
        Product product=findByProduct(id);
        productRepository.delete(product);
        return ConvertCategoryAndProduct.convertProductResponseDto(product);

    }
}
