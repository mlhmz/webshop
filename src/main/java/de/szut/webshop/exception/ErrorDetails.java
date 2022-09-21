package de.szut.webshop.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;

    private String message;

    private String details;
}
