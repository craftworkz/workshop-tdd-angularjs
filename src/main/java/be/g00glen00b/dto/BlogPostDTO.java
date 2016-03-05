package be.g00glen00b.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BlogPostDTO {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String text;
    private LocalDate date;

    public BlogPostDTO(Long id, String title, String text, LocalDate date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
