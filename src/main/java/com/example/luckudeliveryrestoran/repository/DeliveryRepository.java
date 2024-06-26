package com.example.luckudeliveryrestoran.repository;

import com.example.luckudeliveryrestoran.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<Delivery> findByCourierName(String courierName);
}
