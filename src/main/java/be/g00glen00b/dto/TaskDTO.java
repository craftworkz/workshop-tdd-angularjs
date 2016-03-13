package be.g00glen00b.dto;

import javax.validation.constraints.NotNull;

public class TaskDTO {
    private Long id;
    @NotNull(message = "error.task.description.null")
    private String description;
    private boolean completed;

    public TaskDTO(Long id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
