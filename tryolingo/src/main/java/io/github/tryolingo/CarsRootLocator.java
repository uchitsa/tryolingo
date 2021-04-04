package io.github.tryolingo;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;

public class CarsRootLocator extends ODataRootLocator {

    private CarsODataJPAServiceFactory factory;

    public CarsRootLocator(CarsODataJPAServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public ODataServiceFactory getServiceFactory() {
        return this.factory;
    }
}
