package io.github.tryolingo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationResult {
    ResultStatus status;
    String operation;
    String entitySetName;
    String message;
    Object result;
}
