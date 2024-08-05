package com.ecommerce.project.repositories;

import com.ecommerce.project.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
 * Jpa repositoy provides list of generic curd operation function to perform on DB
 * which accepts an Entity<T> and type of primary key of an Entity<E>
 * */
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByCategoryName(String name);
}
