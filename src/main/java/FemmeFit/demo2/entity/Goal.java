package FemmeFit.demo2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @NotNull(message = "Target calories is required")
   @Column(nullable = false)
   private Integer targetCalories;
  @NotNull(message = "Target duration is required")
  @Column(nullable = false)
  private Integer targetDuration;

   @NotNull(message = "Target date is required")
    @Column(nullable = false)
   private LocalDate targetDate;

    @Column
  private String description;

  @Column(columnDefinition = "datetime(6) default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
   @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

   @PrePersist
    protected void onCreate() {
      this.createdAt = LocalDateTime.now();
   }
//    Constructors
    public Goal() {}

  public Goal(Integer targetCalories, Integer targetDuration, LocalDate targetDate,
              String description, User user) {
       this.targetCalories = targetCalories;
      this.targetDuration = targetDuration;
     this.targetDate = targetDate;
    this.description = description;
     this.user = user;
    }

//     Progress calculation methods
    public Double getProgressPercentage(Integer currentCalories, Integer currentDuration) {
        if (targetCalories == 0 || targetDuration == 0) return 0.0;
       double caloriesProgress = (currentCalories.doubleValue() / targetCalories) * 100;
       double durationProgress = (currentDuration.doubleValue() / targetDuration) * 100;
       return (caloriesProgress + durationProgress) / 2;
    }

    public String getStatus() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(targetDate)) return "Overdue";
        if (currentDate.isEqual(targetDate)) return "Due Today";
        return "In Progress";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(Integer targetCalories) {
        this.targetCalories = targetCalories;
    }

    public Integer getTargetDuration() {
        return targetDuration;
    }

    public void setTargetDuration(Integer targetDuration) {
        this.targetDuration = targetDuration;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
