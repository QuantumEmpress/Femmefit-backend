package FemmeFit.demo2.controller;

import FemmeFit.demo2.entity.Goal;
import FemmeFit.demo2.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    @Autowired
    private GoalService goalService;

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
        Goal savedGoal = goalService.createGoal(goal);
        return ResponseEntity.ok(savedGoal);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Goal>> getGoalsByUserId(@PathVariable Long userId) {
        List<Goal> goals = goalService.getGoalsByUserId(userId);
        return ResponseEntity.ok(goals);
    }
}