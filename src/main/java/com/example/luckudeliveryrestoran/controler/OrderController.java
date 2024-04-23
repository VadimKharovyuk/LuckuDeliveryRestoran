package com.example.luckudeliveryrestoran.controler;

import com.example.luckudeliveryrestoran.model.Order;
import com.example.luckudeliveryrestoran.Enum.OrderStatus;
import com.example.luckudeliveryrestoran.service.OrderService;
import com.example.luckudeliveryrestoran.еxception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {


    private  final OrderService orderService;

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders"; // Имя шаблона для отображения заказов
    }
    // Метод для отображения формы редактирования заказа по ID
    @GetMapping("/{orderId}/edit")
    public String editOrder(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "edit-order"; // Имя шаблона для редактирования заказа
    }

    // Метод для сохранения изменений заказа после редактирования
    @PostMapping("/{orderId}/edit")
    public String updateOrder(@PathVariable Long orderId, @ModelAttribute Order updatedOrder) {
        orderService.updateOrderStatus(orderId, updatedOrder.getStatus());
        return "redirect:/orders"; // Перенаправление после сохранения изменений
    }

    @GetMapping("/add")
    public String showAddOrderForm(  Model model) {
        // Добавляем новый объект Order в модель
        model.addAttribute("order", new Order());
        return "add-order"; // Имя шаблона для формы добавления
    }


    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders"; // Перенаправление после добавления
    }
    @GetMapping("/{orderId}/status")
    public String getOrderStatus(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("orderStatus", order.getStatus());
        return "order-status";  // Шаблон, отображающий статус заказа
    }


    @PostMapping("/{orderId}/status")
    public String updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/orders";  // Перенаправление после обновления
    }
    @GetMapping("/{orderId}")
    public String getOrderById(@PathVariable Long orderId, Model model) {
        try {
            Order order = orderService.getOrder(orderId);
            model.addAttribute("order", order);
            return "order-details";  // Имя шаблона для отображения деталей заказа
        } catch (OrderNotFoundException e) {
            // Если заказ не найден, можно перенаправить на страницу ошибки или вернуть статус 404
            return "error/404";  // Имя шаблона для страницы 404
        }
    }

    // Другие методы для редактирования, удаления и просмотра заказов
}
