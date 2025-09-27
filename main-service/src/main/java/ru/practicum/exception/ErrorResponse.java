package ru.practicum.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    private final String status;
    private final String reason;
    private final String message;
    private final String timestamp;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public ErrorResponse(String status, String reason, String message, LocalDateTime timestamp) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.timestamp = timestamp.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}
