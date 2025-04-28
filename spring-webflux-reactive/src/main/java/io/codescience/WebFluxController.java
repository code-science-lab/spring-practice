package io.codescience;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;

import java.time.Duration;

@RestController
public class WebFluxController {

    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono.just("Hello from Mono");
    }

    @GetMapping("/flux")
    public Flux<String> getFlux() {
        return Flux.just("Hello", "-from-", "Flux");
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getStream() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(sequence -> "Stream data - " + sequence)
                   .take(Duration.ofSeconds(10));
    }
} 