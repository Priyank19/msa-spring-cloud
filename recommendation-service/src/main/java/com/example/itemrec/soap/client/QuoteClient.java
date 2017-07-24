package com.example.itemrec.soap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import hello.wsdl.GetQuote;
import hello.wsdl.GetQuoteResponse;

public class QuoteClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);

	public GetQuoteResponse getQuote(String ticker) {

		GetQuote request = new GetQuote();
		request.setSymbol(ticker);

		log.info("Requesting quote for " + ticker);

		GetQuoteResponse response = (GetQuoteResponse) getWebServiceTemplate()
				// To locate the SOAP web service in the wsdl '<http:address...'
				.marshalSendAndReceive("http://www.webservicex.com/stockquote.asmx",
						// Request object
						request,
						// To send a header as in the wsdl@'<soap:operation...'
						new SoapActionCallback("http://www.webserviceX.NET/GetQuote"));
		// The Response needs a marshaller so use @Configuration for it.
		return response;
	}
}
