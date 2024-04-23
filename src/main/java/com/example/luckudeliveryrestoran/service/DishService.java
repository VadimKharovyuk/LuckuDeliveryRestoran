package com.example.luckudeliveryrestoran.service;

import com.example.luckudeliveryrestoran.model.Dish;
import com.example.luckudeliveryrestoran.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService {


    private  final DishRepository dishRepository;

    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    public Dish updateDish(Long id, Dish newDish) {
        Dish dish = getDishById(id);
        if (dish != null) {
            dish.setName(newDish.getName());
            dish.setDescription(newDish.getDescription());
            dish.setPrice(newDish.getPrice());
            return dishRepository.save(dish);
        }
        return null;
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}

