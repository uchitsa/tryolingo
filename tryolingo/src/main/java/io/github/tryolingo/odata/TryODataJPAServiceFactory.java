package io.github.tryolingo.odata;

import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.EntityManagerFactory;

public class TryODataJPAServiceFactory extends ODataJPAServiceFactory {
    final Logger logger = LoggerFactory.getLogger(ru.rusagro.elevator.weight.odata.TryODataJPAServiceFactory.class);
    final private String DEFAULT_ENTITY_UNIT_NAME = "WeightModel";
    final private String DEFAULT_MAPPING_MODEL = "MapModel.xml";
    private EntityManagerFactory emFactory;
    private  ApplicationEventPublisher applicationEventPublisher;
    private  ValidationService validationService;

    public TryODataJPAServiceFactory(EntityManagerFactory emFactory,
                                     ApplicationEventPublisher applicationEventPublisher,
                                     ValidationService validationService){
        super();
        this.emFactory = emFactory;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validationService = validationService;
    }

    @Override
    public ODataSingleProcessor createCustomODataProcessor(ODataJPAContext oDataJPAContext) {
        return new WeightODataJPAProcessor(oDataJPAContext,
			this.applicationEventPublisher,
			this.validationService);
    }

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext oDataJPAContext = getODataJPAContext();
        oDataJPAContext.setEntityManagerFactory(emFactory);
        oDataJPAContext.setJPAEdmExtension(new WeightODataProcessingExtension());
        oDataJPAContext.setPersistenceUnitName(DEFAULT_ENTITY_UNIT_NAME);
        oDataJPAContext.setJPAEdmMappingModel(DEFAULT_MAPPING_MODEL);
        this.setDetailErrors(true);
        return oDataJPAContext;
    }
}

