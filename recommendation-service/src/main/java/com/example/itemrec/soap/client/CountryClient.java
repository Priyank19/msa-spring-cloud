package com.example.itemrec.soap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import country.wsdl.GetCountryRequest;
import country.wsdl.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

	public GetCountryResponse getCountry(String name) {

		GetCountryRequest request = new GetCountryRequest();
		request.setName(name);

		log.info("Requesting country for " + name);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
				// To locate the SOAP web service in the wsdl '<http:address...'
				.marshalSendAndReceive("http://localhost:8080/ws",
						// Request object
						request,
						// To send a header as in the wsdl@'<soap:operation...'
						new SoapActionCallback(""));
		// The Response needs a marshaller so use @Configuration for it.
		return response;
	}
}
