package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.ScheduleDto;
import FemmeFit.demo2.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        try {
            ScheduleDto createdSchedule = scheduleService.createSchedule(scheduleDto);
            return ResponseEntity.ok(createdSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/user/{userId}")
    public List<ScheduleDto> getUserSchedules(@PathVariable String userId) {
        return scheduleService.getUserSchedules(userId);
    }

    @GetMapping("/user/{userId}/date/{date}")
    public List<ScheduleDto> getUserSchedulesByDate(
            @PathVariable String userId,
            @PathVariable LocalDate date) {
        return scheduleService.getUserSchedulesByDate(userId, date);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}
