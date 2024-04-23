package com.example.luckudeliveryrestoran.model;

import com.example.luckudeliveryrestoran.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer_order") // Избегаем конфликта с зарезервированным словом
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String deliveryAddress;
    private String contactNumber;
    private double totalPrice;

    @Enumerated(EnumType.STRING) // Сохраняем значения Enum как строки
    private OrderStatus status; // Используем наш Enum для статуса

    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    @OneToOne(mappedBy = "order")
    private Delivery delivery;
}
