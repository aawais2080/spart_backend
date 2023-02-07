package nl.ordina.spart.controller.helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private int statusCode;
    private String dateTime;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, LocalDateTime dateTime, String message, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");
        this.statusCode = statusCode;
        this.dateTime = dateTime.format(formatter);
        this.message = message;
        this.description = description;
    }
}
