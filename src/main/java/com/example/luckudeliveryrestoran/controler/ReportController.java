package com.example.luckudeliveryrestoran.controler;
import com.example.luckudeliveryrestoran.model.Report;
import com.example.luckudeliveryrestoran.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {
    private  final  ReportRepository reportRepository;

    // Отображение всех отчетов
    @GetMapping
    public String getAllReports(Model model) {
        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);
        return "report_list";  // Возвращает представление с отчетами
    }

    // Добавление нового отчета
    @PostMapping
    public String addReport(@RequestBody Report report) {
        reportRepository.save(report);
        return "redirect:/reports";  // Перенаправление на список отчетов
    }

    // Получение отчета по ID
    @GetMapping("/{id}")
    public String getReportById(@PathVariable Long id, Model model) {
        Report report = reportRepository.findById(id).orElse(null);
        model.addAttribute("report", report);
        return "report_details";  // Возвращает представление с деталями отчета
    }
}

