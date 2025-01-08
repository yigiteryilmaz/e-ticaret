package com.workintech.ecommerce.dto;


public record ProductResponseDto(Long id, String name, Integer quantity,
                                 Double price, Long categoryId) {
}
