package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
@Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductResponseDto> findAll(){
    return productService.findAll();
    }
    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable long id){
    return productService.findById(id);
    }

    @PostMapping("/{id}")
    public ProductResponseDto save(@PathVariable long id, @RequestBody Product product){
    return productService.save(id,product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,@RequestBody Product product){
    return productService.update(id,product);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto delete(@PathVariable long id){
    return productService.delete(id);
    }
}
