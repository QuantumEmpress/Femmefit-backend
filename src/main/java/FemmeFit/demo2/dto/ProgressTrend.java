package FemmeFit.demo2.dto;

import java.util.List;

public class ProgressTrend {
    private List<String> labels;
    private List<Double> caloriesProgress;
    private List<Double> durationProgress;

    // Constructors
    public ProgressTrend() {}

    public ProgressTrend(List<String> labels, List<Double> caloriesProgress,
                         List<Double> durationProgress) {
        this.labels = labels;
        this.caloriesProgress = caloriesProgress;
        this.durationProgress = durationProgress;
    }

    // Getters and Setters
    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Double> getCaloriesProgress() {
        return caloriesProgress;
    }

    public void setCaloriesProgress(List<Double> caloriesProgress) {
        this.caloriesProgress = caloriesProgress;
    }

    public List<Double> getDurationProgress() {
        return durationProgress;
    }

    public void setDurationProgress(List<Double> durationProgress) {
        this.durationProgress = durationProgress;
    }
}