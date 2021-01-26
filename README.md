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

## Docker

- Enterprises are heading towrds microservices architecture
  - Build small focused microservices
  - Flexibility to innovate and build applications in different programming languages like Go, Java, Python, JavaScript..
  - but deployment becomes complex!
  - How we can have one way of deploying  Go, Java, Python, JavaScript ... microservices?
    - Enter Containers!
  - Docker
    - create docker image for each microservices
    - Docker image contains everything a microservice needs to run
      - Application Runtime (JDK, Python or NodeJS)
      - Application code
      - Dependencies
    - You can run these docker containers the same way on any infrastructure
      - local machine
      - Corporate data center
      - Cloud
    - Default Registry(hub.docker.com) > Repository(narayanpratap86/todo-rest-api-h2) > Tag(1.0.0.RELEASE)
    - A docker image a static version like a class, while a dynamic version of a docker image is called container like an Object
    
    ## Docker commands
    |Commands|Description|
    |---|---|
    |`docker run -p 9411:9411 -d narayanpratap86/todo-rest-api-h2:1.0.0.RELEASE`|To run a docker image in back ground(-d) detached mode, expose at port 9411|
    |`docker-compose --version`||
    |`docker-compose up`||
    |`docker push docker.io/in28min/mmv2-currency-exchange-service:0.0.1-SNAPSHOT`||
    |`docker push in28min/mmv2-naming-server:0.0.1-SNAPSHOT`||
    |`docker push in28min/mmv2-currency-conversion-service:0.0.1-SNAPSHOT`||
    |`docker push in28min/mmv2-api-gateway:0.0.1-SNAPSHOT`||
    |`watch -n 0.1 curl http://localhost:8000/sample-api`||
    |`docker container ls`|display the running containers|
    |`docker logs -f <image-id>`|tailing the logs|
    |`docker images`|display all images|
    |`docker container ls -a`|display all the containers with their status|
    |`docker container stop <container-id>`|stop a docker container|
    |`docker run -p 9411:9411 openzipkin/zipkin:2.23`|run zipkin server at http://192.168.99.100:9411/zipkin/|
    ![alt text](https://github.com/pratap86/microservices/blob/master/images/docker_architecture.PNG?raw=true)
    

## Apache Camel Framework with Spring Boot
