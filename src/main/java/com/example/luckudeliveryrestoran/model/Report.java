package com.example.luckudeliveryrestoran.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reports")  // Имя таблицы в базе данных
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Идентификатор отчета

    private String date;  // Дата отчета

    private double totalSales;  // Общие продажи за период

    private String mostPopularDish;  // Самое популярное блюдо

    private double averageDeliveryTime;  // Среднее время доставки
}

