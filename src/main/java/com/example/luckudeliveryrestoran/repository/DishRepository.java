package com.example.luckudeliveryrestoran.repository;

import com.example.luckudeliveryrestoran.model.Category;
import com.example.luckudeliveryrestoran.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findByCategory(Category category);
}
