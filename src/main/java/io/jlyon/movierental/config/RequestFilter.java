package io.jlyon.movierental.config;

import io.jlyon.movierental.MovieRentalApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

@Component
public class RequestFilter {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieRentalApplication.class);
	public ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			log.info("Request: {}", clientRequest.url());
			return next.exchange(clientRequest);
		};
	}
}
