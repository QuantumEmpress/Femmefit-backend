package FemmeFit.demo2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "progress_reports")
public class ProgressReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer totalCaloriesBurned;

    @NotNull
    @Column(nullable = false)
    private Integer totalDuration;

    @NotNull
    @Column(nullable = false)
    private LocalDate reportDate;

    @Column
    private String mostFrequentExercise;

    @Column
    private Integer numberOfWorkouts;

    @Column
    private Double goalProgress;

    @Column
    private String periodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors
    public ProgressReport() {}

    public ProgressReport(Integer totalCaloriesBurned, Integer totalDuration, LocalDate reportDate,
                          String mostFrequentExercise, Integer numberOfWorkouts, Double goalProgress,
                          String periodType, User user) {
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.totalDuration = totalDuration;
        this.reportDate = reportDate;
        this.mostFrequentExercise = mostFrequentExercise;
        this.numberOfWorkouts = numberOfWorkouts;
        this.goalProgress = goalProgress;
        this.periodType = periodType;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(Integer totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getMostFrequentExercise() {
        return mostFrequentExercise;
    }

    public void setMostFrequentExercise(String mostFrequentExercise) {
        this.mostFrequentExercise = mostFrequentExercise;
    }

    public Integer getNumberOfWorkouts() {
        return numberOfWorkouts;
    }

    public void setNumberOfWorkouts(Integer numberOfWorkouts) {
        this.numberOfWorkouts = numberOfWorkouts;
    }

    public Double getGoalProgress() {
        return goalProgress;
    }

    public void setGoalProgress(Double goalProgress) {
        this.goalProgress = goalProgress;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}