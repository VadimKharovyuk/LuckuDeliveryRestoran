package com.example.luckudeliveryrestoran.controler;

import com.example.luckudeliveryrestoran.model.Dish;
import com.example.luckudeliveryrestoran.model.Order;
import com.example.luckudeliveryrestoran.Enum.OrderStatus;
import com.example.luckudeliveryrestoran.service.DishService;
import com.example.luckudeliveryrestoran.service.OrderService;
import com.example.luckudeliveryrestoran.еxception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {


    private  final OrderService orderService;
    private final DishService dishService;

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
    public String showAddOrderForm(Model model) {
        // Предположим, что вы используете сервис для получения всех блюд
        List<Dish> dishes = dishService.getAllDishes(); // Получение списка всех блюд

        Order newOrder = new Order(); // Новый объект заказа

        model.addAttribute("order", newOrder); // Добавляем пустой заказ в модель
        model.addAttribute("dishes", dishes);  // Добавляем список блюд в модель

        return "add-order"; // Имя HTML-шаблона
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
//    @GetMapping("/{orderId}")
//    public String getOrderById(@PathVariable Long orderId, Model model) {
//        try {
//            Order order = orderService.getOrder(orderId);
//            model.addAttribute("order", order);
//            return "order-details";  // Имя шаблона для отображения деталей заказа
//        } catch (OrderNotFoundException e) {
//            // Если заказ не найден, можно перенаправить на страницу ошибки или вернуть статус 404
//            return "error/404";  // Имя шаблона для страницы 404
//        }
//    }
    // Метод для обработки запроса на удаление заказа по его ID
    @PostMapping("/{orderId}/delete")
    public String deleteOrder(@PathVariable Long orderId, Model model) {
        // Удаление заказа с помощью сервиса
        orderService.deleteOrder(orderId);

        // Перенаправление к списку заказов после успешного удаления
        return "redirect:/orders";
    }

//    @PostMapping("/orders/{orderId}/status")
//    public String updateOrderStatus(@PathVariable Long orderId, @RequestParam("newStatus") String newStatus, Model model) {
//        Order order = orderService.getOrder(orderId);
//
//        // Преобразование строки в enum
//        OrderStatus status = OrderStatus.valueOf(newStatus);  // Преобразование строки в enum
//
//        order.setStatus(status);  // Устанавливаем статус заказа
//        orderService.updateOrder(order);  // Сохраняем изменения
//
//        // Возвращение к шаблону с обновленным статусом заказа
//        model.addAttribute("order", order);
//        return "order-details";  // Здесь укажите правильный путь к шаблону деталей заказа
//    }
    @GetMapping("/status") // Маршрут для отображения статуса всех заказов
    public String getAllOrderStatuses(Model model) {
        List<Order> allOrders = orderService.getAllOrders(); // Получение всех заказов
        model.addAttribute("orders", allOrders); // Передача заказов в модель
        return "order-statuses"; // Имя шаблона Thymeleaf для отображения статусов заказов
    }
    @GetMapping("/{orderId}")
    public String getOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderStatuses", OrderStatus.values());  // Передаем все возможные статусы
        return "order-details";
    }

    @PostMapping("/{orderId}/updateStatus")
    public String updateOrderStatus(@PathVariable Long orderId, @RequestParam("newStatus") String newStatus, Model model) {
        Order order = orderService.getOrder(orderId);
        OrderStatus status = OrderStatus.valueOf(newStatus);  // Преобразуем строку в Enum
        order.setStatus(status);
        orderService.updateOrder(order);
        return "redirect:/orders/" + orderId;
    }


    // Другие методы для редактирования, удаления и просмотра заказов
}
