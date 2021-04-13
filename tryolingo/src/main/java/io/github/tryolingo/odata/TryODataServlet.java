package io.github.tryolingo.odata;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@Component
public class TryODataServlet extends ODataServlet {

	private EntityManagerFactory emFactory;
	private ApplicationEventPublisher applicationEventPublisher;
	private ValidationService validationService;

	@Autowired
	public TryODataServlet(  EntityManagerFactory emFactory,
								ApplicationEventPublisher applicationEventPublisher,
								ValidationService validationService){
		super();
		this.emFactory = emFactory;
		this.applicationEventPublisher = applicationEventPublisher;
		this.validationService = validationService;

	}

	@Override
	protected ODataServiceFactory getServiceFactory(HttpServletRequest request){
		return  new  TryODataJPAServiceFactory(emFactory, applicationEventPublisher, validationService);
	}
}
