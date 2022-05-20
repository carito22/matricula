package com.carol.matricula.exceptions;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private LocalDateTime datetime;
    private String message;
    private String path;
}
