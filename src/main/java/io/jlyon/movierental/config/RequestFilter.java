package io.jlyon.movierental.config;

import io.jlyon.movierental.MovieRentalApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Component
public class RequestFilter {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieRentalApplication.class);
	public ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor((clientRequest) -> {
			logRequest(clientRequest);
			return Mono.just(clientRequest);
		});
	}

	private void logRequest(ClientRequest req) {
		log.info("Request: {}", req.url());
		log.trace("Headers: {}", req.headers());
		log.trace("Body: {}", req.body());
	}
}
