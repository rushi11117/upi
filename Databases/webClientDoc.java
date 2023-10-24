// 1. GET Request
Mono<String> getResponseMono = webClient
        .get()
        .uri("/resource")
        .retrieve()
        .bodyToMono(String.class);

// 2. POST Request with JSON payload
MyObject payload = new MyObject("data");
Mono<MyObject> postResponseMono = webClient
        .post()
        .uri("/create")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(payload))
        .retrieve()
        .bodyToMono(MyObject.class);

// 3. PUT Request with JSON payload
MyObject updatedPayload = new MyObject("updatedData");
Mono<MyObject> putResponseMono = webClient
        .put()
        .uri("/update/{id}", 123)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(updatedPayload))
        .retrieve()
        .bodyToMono(MyObject.class);

// 4. DELETE Request
Mono<Void> deleteResponseMono = webClient
        .method(HttpMethod.DELETE)
        .uri("/delete/{id}", 456)
        .retrieve()
        .bodyToMono(Void.class);

// 5. Exchange method (for more control and access to response details)
Mono<ClientResponse> exchangeResponseMono = webClient
        .method(HttpMethod.PATCH)
        .uri("/patch")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(someData))
        .exchange();

// 6. Error handling
Mono<MyObject> errorHandlingMono = webClient
        .get()
        .uri("/invalid")
        .retrieve()
        .onStatus(
            status -> status.is4xxClientError() || status.is5xxServerError(),
            clientResponse -> Mono.error(new MyCustomException("Request failed"))
        )
        .bodyToMono(MyObject.class);

// 7. Handling response as a String
Mono<String> responseAsStringMono = webClient
        .get()
        .uri("/string-resource")
        .retrieve()
        .bodyToMono(String.class);

// Remember to subscribe to the Mono to actually trigger the requests, e.g., getResponseMono.subscribe()
