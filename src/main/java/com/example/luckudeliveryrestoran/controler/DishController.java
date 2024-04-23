package com.example.luckudeliveryrestoran.controler;

import com.example.luckudeliveryrestoran.model.Dish;
import com.example.luckudeliveryrestoran.service.CategoryService;
import com.example.luckudeliveryrestoran.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
@AllArgsConstructor
public class DishController {


    private final DishService dishService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAllDishes(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "dishes";
    }

    @GetMapping("/{id}")
    public String getDishById(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDishById(id);
        model.addAttribute("dish", dish);
        return "dish";
    }



    @DeleteMapping("/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/dishes";
    }
    // Метод для отображения формы
    @GetMapping("/add")
    public String showAddDishForm(Model model) {
        // Добавление пустого объекта Dish для связывания с формой
        model.addAttribute("dish", new Dish());

        // Добавление списка всех категорий для отображения в выпадающем списке
        model.addAttribute("categories", categoryService.getAllCategories());

        return "add-dish"; // Имя шаблона для формы
    }

    // Метод для обработки данных из формы
    @PostMapping("/add")
    public String addDish(@ModelAttribute Dish dish) {
        // Сохранение блюда в БД через сервис
        dishService.addDish(dish);
        return "redirect:/dishes"; // Перенаправление на список блюд после успешного добавления
    }
}

