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
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

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

    // Другие методы для управления заказами
}
