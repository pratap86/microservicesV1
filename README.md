# Microservices with SpringBoot V2.4

| Important updates||
| --- | --- |
|`Latest version of Spring Boot & Cloud`| 1. Spring Cloud LoadBalencer instead of Ribbon<br>2. Spring Cloud Gateway instead of Zuul<br>3. Resilience4j instead of Hystrix |
|`Docker Containerize microservices`|Run microservices using Docker & Docker Compose|
|`Kubernetes`|Orchestrate all your microservices with Kubernetes|

| Microservice Components | Port Used |
| --- | --- |
| `Limits Microservice` | 8080, 8081, .. |
|`Spring CLoud Config Server`<br><b>A Centralized Config Server</b>|8888|
|`Currency Exchange Microservice`|8000, 8001, 8002, ..|
|`Currency Conversion Microservice`|8100, 8101, 8102, ..|
|`Netflix Eureka Naming Server`|8761|
|`API Gateway`|8765|
|`Zipkin Distributed Tracing Server`|9411|

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
