package com.example.luckudeliveryrestoran.service;
import com.example.luckudeliveryrestoran.model.Order;
import com.example.luckudeliveryrestoran.Enum.OrderStatus;
import com.example.luckudeliveryrestoran.repository.OrderRepository;
import com.example.luckudeliveryrestoran.еxception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {


    private  final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
//    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
//        order.setStatus(newStatus);
//        orderRepository.save(order);
//    }

    public Order getOrder(Long orderId) {
        // Получаем заказ по ID
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        // Если заказ найден, возвращаем его
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            // Если заказ не найден, можно выбросить исключение
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    // Метод для обновления заказа по ID
//    public void updateOrder(Long orderId, Order updatedOrder) {
//        // Проверка наличия заказа
//        Order existingOrder = getOrder(orderId);
//
//        // Обновление полей заказа
//        existingOrder.setCustomerName(updatedOrder.getCustomerName());
//        existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
//        existingOrder.setContactNumber(updatedOrder.getContactNumber());
//        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
//        existingOrder.setStatus(updatedOrder.getStatus());
//        existingOrder.setDishes(updatedOrder.getDishes());
//
//        // Сохранение обновленного заказа
//        orderRepository.save(existingOrder);
//    }

    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


    // Другие методы для управления заказами
}
