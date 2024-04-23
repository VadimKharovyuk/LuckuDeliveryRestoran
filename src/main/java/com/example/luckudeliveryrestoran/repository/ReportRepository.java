package com.example.luckudeliveryrestoran.repository;

import com.example.luckudeliveryrestoran.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> findByDate(String date);
}
