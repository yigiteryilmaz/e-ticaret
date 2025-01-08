package com.workintech.ecommerce.util;

import com.workintech.ecommerce.dto.CategoryResponseDto;
import com.workintech.ecommerce.dto.ProductResponseDto;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ConvertCategoryAndProduct {
    public static List<CategoryResponseDto> converCategoryList(List<Category> categories) {
        List<CategoryResponseDto> resultCategories=new ArrayList<>();

        categories.forEach(category -> resultCategories.add(new CategoryResponseDto(category.getId(), category.getName(), category.getDescription())));

       return resultCategories;
    }

    public static CategoryResponseDto converCategoryResponseDto(Category category) {

         return new CategoryResponseDto(category.getId(), category.getName(), category.getDescription());
    }

    public static List<ProductResponseDto> convertProductList(List<Product> products) {
        List<ProductResponseDto> resultProducts=new ArrayList<>();

        products.forEach(product -> resultProducts.add(new ProductResponseDto(product.getId(), product.getName(),
                product.getQuantity(), product.getPrice(), product.getCategory().getId())));

        return resultProducts;
    }

    public static ProductResponseDto convertProductResponseDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getQuantity(),
                product.getPrice(), product.getCategory().getId());
    }
}
