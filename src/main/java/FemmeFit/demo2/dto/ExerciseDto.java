
package FemmeFit.demo2.dto;

import FemmeFit.demo2.entity.Workout;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


public class ExerciseDto {

    private Long id;

    private String name;
    private int reps;
    private int sets;
    private int restInterval;


    // Getters and Setters

    public ExerciseDto(Long id, String name, int reps, int sets, int restInterval) {
        this.id = id;
        this.name = name;
        this.restInterval = restInterval;
        this.reps = reps;
        this.sets = sets;
    }

    public ExerciseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRestInterval() {
        return restInterval;
    }

    public void setRestInterval(int restInterval) {
        this.restInterval = restInterval;
    }
}