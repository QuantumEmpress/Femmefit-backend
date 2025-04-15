package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.GoalProgressDTO;
import FemmeFit.demo2.dto.ProgressTrend;
import FemmeFit.demo2.entity.ProgressReport;
import FemmeFit.demo2.service.ProgressReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class ProgressAnalyticsController {

    @Autowired
    private ProgressReportService progressReportService;

    // Manual report operations
    @PostMapping("/user/{userId}/report")
    public ProgressReport createManualReport(
            @RequestBody ProgressReport progressReport,
            @PathVariable Long userId) {
        return progressReportService.createProgressReport(progressReport, userId);
    }

    @GetMapping("/user/{userId}/reports")
    public List<ProgressReport> getAllUserReports(@PathVariable Long userId) {
        return progressReportService.getProgressReportsByUserId(userId);
    }

    // Report generation endpoints
    @GetMapping("/user/{userId}/generate")
    public ProgressReport generateCustomReport(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return progressReportService.generateProgressReport(userId, startDate, endDate, "custom");
    }

    @GetMapping("/user/{userId}/weekly")
    public ProgressReport generateWeeklyReport(@PathVariable Long userId) {
        return progressReportService.generateWeeklyReport(userId);
    }

    @GetMapping("/user/{userId}/monthly")
    public ProgressReport generateMonthlyReport(@PathVariable Long userId) {
        return progressReportService.generateMonthlyReport(userId);
    }

    // Progress analysis endpoints
    @GetMapping("/user/{userId}/trend")
    public ProgressTrend getProgressTrend(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "4") int weeks) {
        return progressReportService.getProgressTrend(userId, weeks);
    }

    @GetMapping("/user/{userId}/goals-progress")
    public Map<String, Object> getGoalsProgress(@PathVariable Long userId) {
        return progressReportService.calculateGoalsProgress(userId);
    }

    @GetMapping("/user/{userId}/current-progress")
    public Map<String, Object> getCurrentProgressSnapshot(@PathVariable Long userId) {
        return progressReportService.getCurrentProgressSnapshot(userId);
    }
}