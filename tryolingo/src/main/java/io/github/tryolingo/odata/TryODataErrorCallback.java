package io.github.tryolingo.odata;

import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.processor.ODataErrorCallback;
import org.apache.olingo.odata2.api.processor.ODataErrorContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;

public class TryODataErrorCallback implements ODataErrorCallback {
	@Override
	public ODataResponse handleError(ODataErrorContext context) throws ODataApplicationException {
		return null;
	}
}
