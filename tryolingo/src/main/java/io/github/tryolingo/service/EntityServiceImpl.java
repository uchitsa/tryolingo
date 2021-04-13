package io.github.tryolingo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EntityServiceImpl implements EntityService {

    Logger logger = LoggerFactory.getLogger(EntityServiceImpl.class);

    @Autowired
    ODataClientService oDataClientService;

    @Autowired
    OperationInstanceService operationInstanceService;

    @Autowired
    EntityInfoService entityInfoService;


    @Override
    public List<OperationResult> initOperation() throws OperationExeption {
        //entityInfoService.deleteAll();
        List<String> entitiesName = null;
        try {
            entitiesName = oDataClientService.getEntitySetNames();
        } catch (ODataClientException e) {
            logger.error(MessageFormat.format("Entity list not been loaded for initial service", e.getMessage()), e);
            throw new OperationExeption("Error in initialization service", e);
        }
        return entitiesName.stream()
                .map(name -> operationInstanceService.getOperation(OperationType.init, name))
                .filter(Objects::nonNull)
                .map(OperationWrapper::new)
                .map(operation -> operation.doOperation())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OperationResult singleOperation(String entitySetName, String operation) {
        if (entitySetName == null || entitySetName.isEmpty() || operation == null || operation.isEmpty()) {
            return new OperationResult(
                    ResultSatus.ERROR,
                    "",
                    "",
                    "Error:  empty entity ",
                    new OperationExeption()
            );
        }

        try {
            return operationInstanceService.getOperation(OperationType.valueOf(operation), entitySetName).doOperation();
        } catch (Exception e) {
            logger.error(MessageFormat.format("Operation - {0} for entity - {1} error with - {2}", operation, entitySetName, e.getMessage()), e);
            return new OperationResult(
                    ResultSatus.ERROR,
                    operation,
                    entitySetName,
                    e.getMessage(),
                    new OperationExeption()
            );
        }
    }

    @Override
    public List<OperationResult> massOperation(String operation) {
        return entityInfoService.getAll()
                .stream()
                .map(name -> this.singleOperation(name.getName(), operation))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationResult> massOperation(List<Operation> operations) {
        return operations
                .stream()
                .map(operation -> this.singleOperation(operation.getEntityName(), operation.getOperation()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private class OperationWrapper implements EntityOperationService {

        private EntityOperationService operationService;

        OperationWrapper(EntityOperationService operationService) {
            this.operationService = operationService;
        }

        @Override
        public OperationResult doOperation() {
            try {
                return this.operationService.doOperation();
            } catch (Exception e) {
                logger.error(MessageFormat.format("Entity operation error: {0}", e.getMessage()), e);
                return null;
            }
        }
    }

}
