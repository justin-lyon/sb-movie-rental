package io.jlyon.movierental.config;

import io.jlyon.movierental.MovieRentalApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Component
public class ResponseFilter {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieRentalApplication.class);
	public ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor((clientResponse) -> {
			logResponse(clientResponse);
			return Mono.just(clientResponse);
		});
	}

	private void logResponse(ClientResponse res) {
		log.info("Request: {}", res.statusCode());
		log.trace("Headers: {}", res.toString());
	}
}
