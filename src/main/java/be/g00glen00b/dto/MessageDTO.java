package be.g00glen00b.dto;

public class MessageDTO {
    private String code;
    private String description;

    public MessageDTO(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
