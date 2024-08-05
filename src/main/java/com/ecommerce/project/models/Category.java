package com.ecommerce.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Repository;

import javax.annotation.processing.Generated;

@Entity(name="categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "order_seq")
    @SequenceGenerator(name = "order_seq" , sequenceName = "order_sequence" , allocationSize = 1)
    private Long categoryId;

    @NotBlank(message = "Category name cannot be blank or null.")
    private String categoryName;
}
