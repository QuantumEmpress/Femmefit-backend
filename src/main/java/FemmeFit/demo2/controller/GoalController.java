package FemmeFit.demo2.controller;

import FemmeFit.demo2.dto.GoalProgressDTO;
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

    @PostMapping("/user/{userId}")
    public Goal createGoal(@RequestBody Goal goal, @PathVariable Long userId) {
        return goalService.createGoal(goal, userId);
    }

    @GetMapping("/user/{userId}")
    public List<Goal> getGoalsByUserId(@PathVariable Long userId) {
        return goalService.getGoalsByUserId(userId);
    }

    @GetMapping("/{goalId}/progress")
    public GoalProgressDTO getGoalProgress(@PathVariable Long goalId) {
        return goalService.getGoalProgress(goalId);
    }

    @GetMapping("/user/{userId}/progress")
    public List<GoalProgressDTO> getAllGoalsProgress(@PathVariable Long userId) {
        return goalService.getAllGoalsProgress(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}