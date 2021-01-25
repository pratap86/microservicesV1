# Microservices with SpringBoot V2.4

| Important updates||
| --- | --- |
|`Latest version of Spring Boot & Cloud`| 1. Spring Cloud LoadBalencer instead of Ribbon<br>netflix eureka client comes with spring cloud load balencer<br>2. Spring Cloud Gateway instead of Zuul<br>3. Resilience4j instead of Hystrix |
|`Docker Containerize microservices`|Run microservices using Docker & Docker Compose|
|`Kubernetes`|Orchestrate all your microservices with Kubernetes|

- Currency Conversion Microservice talks to Currency Exchange Microservice

| Microservice Components | Port Used |
| --- | --- |
| `Limits Microservice` | 8080, 8081, .. |
|`Spring CLoud Config Server`<br><b>A Centralized Config Server</b>|8888|
|`Currency Exchange Microservice`|8000, 8001, 8002, ..|
|`Currency Conversion Microservice`|8100, 8101, 8102, ..|
|`Netflix Eureka Naming Server`|8761|
|`API Gateway`|8765|
|`Zipkin Distributed Tracing Server`|9411|

- Microservices URLs before implementing custom routing

|Microservice|URL|
|---|---|
|`CURRENCY-EXCHANGE`|http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR|
|`CURRENCY-CONVERSION`|http://localhost:8765/currency-conversion/currency-conversion/resttemplate/from/USD/to/INR/quantity/10|
|`CURRENCY-CONVERSION`|http://localhost:8765/currency-conversion/currency-conversion/feign/from/USD/to/INR/quantity/10|

- Microservices URLs after implementing custom routing

|Microservice|URL|
|---|---|
|`CURRENCY-EXCHANGE`|http://localhost:8765/currency-exchange/from/USD/to/INR|
|`CURRENCY-CONVERSION`|http://localhost:8765/currency-conversion/resttemplate/from/USD/to/INR/quantity/10|
|`CURRENCY-CONVERSION`|http://localhost:8765/currency-conversion/feign/from/USD/to/INR/quantity/10|

# Curcuit Breaker
- Microservice1 -> Microservice2 -> Microservice3 -> Microservice4 -> Microservice5
  - what if one of the above service is slow or down?
    - impact entire chain?
  - Questions?
    - Can we return a fallback response if a service is down?
    - Can we implement a circuit breaker pattern to reduce the load?
    - Can we retry the request in case of temporary failures?
    - Can we implemnt rete limiting?
  - Solution : Circuit Breaker Framework - <b>Resilience4j</b>
  - CircuitBreaker is implemented via a finite state machine with three normal states: CLOSED, OPEN and HALF_OPEN

# HTTP Status Codes
|LEVEL|MEASSAGE|
| --- | --- |
|`100-level (Informational)`|`Server acknowledges a request`|
|`200-level (Success)`|`Server completed the request as expected`|
|`300-level (Redirection)`|`Client needs to perform further actions to complete the request`|
|`400-level (Client error)`|`Client sent an invalid request`|
|`500-level (Server error)`|`Server failed to fulfill a valid request due to an error with server`|

# HTTP Basic Responses
| Code | Message |
| --- | --- |
| `200` | `SUCCESS` |
| `201` | `CREATED` |
| `400` | `BAD REQUEST` |
| `401` | `UNAUTHORISED` |
| `404` | `RESOURCE NOT FOUND` |
|`412 `|`Precondition Failed — One or more conditions in the request header fields evaluated to false`|
| `500` | `INTERNAL SERVER ERROR` |
| `502` | `BAD GATEWAY` |
|`503`|`SERVICE UNAVAILABLE`|

## Apache Camel Framework with Spring Boot
