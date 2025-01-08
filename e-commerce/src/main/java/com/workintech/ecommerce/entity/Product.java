package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product",schema = "ecommerce")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank(message = "ürün isim alanı boş bırakılamaz!")
    @Size(min = 2,message = "ürün isim alanı en az 2 karakterden oluşmalıdır!")
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @NotNull(message = " ürün fiyatı boş bırakılamaz!")
    @Range(min = 0,message = "ürün fiyatı 0'dan büyük olmalıdır!")
    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

}
