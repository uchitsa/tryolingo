package io.github.tryolingo.service;

import io.github.tryolingo.dto.OperationResult;

import java.util.List;

public interface EntityService {
    List<OperationResult> initOperation() throws OperationExeption;
    OperationResult singleOperation(String entitySetName, String operation);
}