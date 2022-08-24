## Definition
<p>Microservices is an approach to developing a single application as a suite of small services, each running on its own process and communicating with light weight mechanisms(JSON), built around business capabilities and independently deployable by fully automated deployment machinery.</p>
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

- Microservices initial URLs

|Microservice|URL|
|---|---|
|`CURRENCY-EXCHANGE`|http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR|
|`CURRENCY-CONVERSION`|http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10|

- Microservices Lower Case URLs

|Microservice|URL|
|---|---|
|`CURRENCY-EXCHANGE`|http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR|
|`CURRENCY-CONVERSION`|http://localhost:8765/currency-conversion/currency-conversion/from/USD/to/INR/quantity/10|

- Microservices URLs after implementing custom routing

|Microservice|URL|
|---|---|
|`currency-exchange`|http://localhost:8765/currency-exchange/from/USD/to/INR|
|`currency-conversion`|http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10|

### @RefreshScope to refreshing proerties
### 12 Factors App
1. Codebase
2. Dependencies
3. Config
4. Backing Services
5. Build, Run & Release
6. Processes
7. Port binding
8. Concurrency
9. Disposability
10. Dev/Prod Parity
11. Logs
12. Admin Process
### Monitoring microservices using Prometheus and Grafana

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

![alt text](https://github.com/pratap86/microservices/blob/master/images/docker_architecture.PNG?raw=true)

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
    - Maven Plugin to build docker images : mvn spring-boot:build-image -DSkipTests
    - Images
              
        |service|image|
        |---|---|
        |`currency-exchange-service`|narayanpratap86/mmv2-currency-exchange-service:0.0.1-SNAPSHOT|
        |`currency-conversion-service`|narayanpratap86/mmv2-currency-conversion-service:0.0.1-SNAPSHOT|
        |`api-gateway`|docker.io/narayanpratap86/mmv2-api-gateway:0.0.1-SNAPSHOT|
        |`naming-server`|narayanpratap86/mmv2-naming-server:0.0.1-SNAPSHOT|
    
    ## Docker commands
    |Commands|Description|
    |---|---|
    |`docker-machine ip`|show docker ip details|
    |`docker run -p 9411:9411 -d narayanpratap86/todo-rest-api-h2:1.0.0.RELEASE`|To run a docker image in back ground(-d) detached mode, expose at port 9411|
    |`docker-compose --version`|docker-compose version|
    |`docker-compose up`|start multple services through docker compose|
    |`docker push narayanpratap86/mmv2-currency-exchange-service:0.0.1-SNAPSHOT`|push the image in to your docker repository|
    |`watch -n 0.1 curl http://localhost:8000/sample-api`||
    |`docker container ls`|display the running containers|
    |`docker logs -f <image-id>`|tailing the logs|
    |`docker images`|display all images|
    |`docker tag <repository>:<version-name> <repository>:<new-version-name>`|create existing image with new tag name|
    |`docker image history <image-id> OR <repository-name-with-tag>`|display image history|
    |`docker image inspect <image-id> OR <repository-name-with-tag>`|display a JSON form details|
    |`docker image remove <image-id> OR <repository-name-with-tag>`|to remove a image from local registry|
    |`docker container ls -a`|display all the containers with their status|
    |`docker container stop <container-id>`|stop a docker container, SIGTERM - Graceful shutdown|
    |`docker container kill <container-id>`|immediatly terminates the process, SIGKILL|
    |`docker container pause <container-id>`|just pause a container in to a specific state|
    |`docker container unpause <container-id>`|unpause a container|
    |`docker container inspect <container-id>`|display a JSON form details|
    |`docker container prune`|This will remove all the stopped containers|
    |`docker top <container-id>`|to see what is the running process on top|
    |`docker container events`|To see various events once a container trying to start|
    |`docker stats <container-id>(optional)`|container statistics|
    |`docker run -p 9411:9411 openzipkin/zipkin:2.23`|run zipkin server at http://192.168.99.100:9411/zipkin/|
    |`docker run -p 61616:61616 -p 8161:8161 rmohr/activemq`|To launch ActiveMQ, The JMX broker listens on port 61616 and the Web Console on port 8161|
    |`docker run -d -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=testdb -e MYSQL_USER=pratap<br> -e MYSQL_PASSWORD=pratap -p 3306:3306 mysql:5.7`|Run mysql as docker image |
    |`docker network ls`|networks list; bridge, host and none, internal communication must be happen only with custom network|
    |`docker volume ls`|volumes list|
    |`mysqlsh`|mysql shell|
    |`\connect pratap@192.168.99.100:3306`|connect docker-mysql through mysql shell|
    
## Dockerise your service with Plugins
    
 ### Using integrated spring boot build-image goal
    
``` ruby
    <plugin>
		  <groupId>org.springframework.boot</groupId>
		  <artifactId>spring-boot-maven-plugin</artifactId>
		  <configuration>
		    <image>
		      <name>narayanpratap86/pnv2-${project.artifactId}:${project.version}</name>
		    </image>
		    <pullPolicy>IF_NOT_PRESENT</pullPolicy>
      </configuration>
   </plugin>
     
   And from command prompt just run the command;
   mvn spring-boot:build-image
```
    
### Using  GoogleContainerTools/jib
    
 ``` ruby
  <project>
  ...
  <build>
    <plugins>
      ...
      <plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>jib-maven-plugin</artifactId>
        <version>3.1.4</version>
        <configuration>
          <to>
            <image>narayanpratap86/pnv2-${project.artifactId}:${project.version}</image>
          </to>
        </configuration>
      </plugin>
      ...
    </plugins>
  </build>
  ...
</project>

And run the command;
 mvn compile com.google.cloud.tools:jib-maven-plugin:3.1.4:dockerBuild
```
    
## Docker Compose

- Compose is a tool for defining and running multi-container Docker applications. With Compose, you use a YAML file to configure your application’s services. Then, with a single command, you create and start all the services from your configuration.
- Compose works in all environments: production, staging, development, testing, as well as CI workflows.
    - Using Compose is basically a three-step process:
      -  Define your app’s environment with a Dockerfile so it can be reproduced anywhere.
      -  Define the services that make up your app in docker-compose.yml so they can be run together in an isolated environment.
      -  Run docker-compose up and Compose starts and runs your entire app.
    - docker-compose up Run this command where docker-compose.yaml file present.

|Docker Compose|Commands|
|---|---|
|`docker-compose up`|search the docker-compose.yaml file and run the containers|
|`docker-compose up -f custom docker compose file name`|search the docker-compose-custom-file.yaml file and run the containers|
|`up, restart, build, scale, stop, kill, logs, ps`||

## Container Orchestration
- Requirment - I have to 10 instances of miroservice A container, 15 instances of microservice B container
- Typical Features;
  - Auto Scaling - scale containers based on demand
  - Service Discovery - Help microservices find one another
  - Load Balancer - Distribute load among multiple instances of a microservice
  - Self Healing - Do health checks and replace failing instances
  - Zero Downtime Deployment - Release new version without downtime

### Container Orchestration Options
- AWS Specific
  - AWS Elastic Container Service (ECS)
  - AWS Fargate:Serverless version of AWS ECS

- Cloud Neutral - Kubernetes
  - AWS - Elastic Kubernetes Service(EKS)
  - Azure - Azure Kubernetes Service(AKS)
  - GCP - Google Kubernetes Engine(GKE)
  - EKS & AKS does not have free tier!

#### Kubernetes(KOO-BER-NET-EEZ) Concepts
- Cluster
	- A Cluster is a combination of nodes a worker node(Run your application) as well as a master node(Manage your worker nodes).
- Nodes
	- A Node is a vertual server
- Pods
	- A node contains multiple Pods while a Pod contains multiple containers.
- Containers
- Deployments
- Service
- Replica Sets
- Host of other features

#### K8S Deployment commands

|Commands|Description|
|---|---|
|`gcloud container clusters get-credentials pratap86-cluster(cluster-name) --zone us-central1-c(zone) --project concise-display-286412(project-id)`|To connect newly created cluster|
|`kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE`|TO create a deployment|
|`kubectl expose deployment hello-world-rest-api --type=LoadBalancer --port=8080`|exposed prev created deployment|
|`kubectl get events`|displays events for creating a deployment|
|`kubectl get pods -o wide`|display pod status with their IP|
|`kubectl describe pod pod-name`|display pod description in details like Namespace, Annotations - Meta Info about Pod, Status|

#### Google Cloud Concepts
- Regions
- Zones
- Projects
- Compute Engine
- 
### Kubernetes K8s
- in greek means Helmsman or Ship Pilot

- Container Orchestration : Group containers to form a cluster
  - Container Orchestration is process of forming a cluster of containers that work with each other with all below non-functional requirments are met.
    - Fault Tolerance
    - On-demand Scalability
    - Auto Discovery
    - Performance
    - Auto Update and Rollback
- <b>Kubernetes Object Model</b>

|K8s Objects/commands|Definition|
|---|---|
|`Service`||
|`Namespace`|Logicaly group the set of applications, & they have own namespace in K8s cluster<br>kubectl get namespaces|
|`Deployment`|Manage ReplicaSet & Pods|
|`ReplicaSet`|Help and monitors the Pods, bcz Pods are not self heiling or Fault tolerance|
|`Pod`|A lowest Object, logical group of docker containers|
|`minikube start --memory=3000MB --vm-driver=virtualbox`|start minikube|
|`minikube status`|to check status|
|`minikube stop`|stop minikube|
|`minikube delete`|delete minikube|
|`kubectl explain pods`| Pod is a collection of containers that can run on a host.<br>This resource is created by clients and scheduled onto hosts.|
|`kubectl get pods`|display the pods list|
|`kubectl describe pod <pod-id>`|describe pod|
|`kubectl config view`|k8s configuration yaml view|
|`kubectl run firstpod --image=nginx`|create and run pod|
|`kubectl get pods`|pod list|
|`kubectl describe pod pod-name`|describe pod|
|`kubectl exec -it pod-name -- /bin/bash`|go inside a pod|
|`kubectl get pod pod-name -o yaml`|pod yaml description|
|`kubectl delete pod pod-name`|delete existing runnig pod|
|`kubectl delete all --all`|to delete all pods|
|`kubectl create -f file-name.yaml`|create pod through yml file|
|`kubectl delete -f file-name.yml`|To delete a pod|
|`kubectl get pods`|after create the pod, list the pod with their status in default namespace|
|`kubectl describe pods`|show the internal executions status, see the all events behind the pod |
|`kubectl exec -it pod-name --container container-name -- /bin/bash`|To go inside a perticular container, -it means intractive mode|
|`kubectl get pods -o yaml`|get the pod Phase, track any issue, go from bottom to up till status|
|`kubectl get all --show-labels`|display all labels|
|`kubectl get all --selector='app=fp' 'label-name'`<br>also use app!=fp, app in (fp, red)|to fetch only selected pod|
|`kubectl get ns or namespaces`|list namespaces|
|`kubectl create ns namespace-name`|create own namespace|
|`kubectl create -f firstpod.yml --namespace firstns`|create pod inside own namespace|
|`kubectl get pods --namespace firstns`|list pods inside perticular namespace|
|`kubectl config view`|view the namespace currently set|
|`kubectl config set-context --current --namespace firstns`|set the namespace in current context ie minikube|
|`kubectl create -f firstpod.yml --dry-run=client`|to verify our yml syntax is correct? actually not created the pod|
|`kubectl get pod/pod-name -o yaml --export=true`|to view the internally created yaml file|
|`kubectl explain pod`|Documentation of resource|
|`kubectl explain pod.spec`|one more level inside|
|`minikube dashboard`|launch the web dashboard|
|`kubectl get deployments`|list the deployment|
|`kubectl create -f deployment-file-name`|create deployment|
|`kubectl create -f service-file-name`|create the service|
|`kubectl get services`|list the services|
|`minikube ip`|display minikube ip|
|`minikube service service-name`|to launch the created service|
|`kubectl get deploy deployment-name -o yaml`|to view deplyed yaml file|
|`kubectl get deploy deployment-name -o json`|in json format|
|`kubectl replace -f deployment-file-name`|update an existing deployment|
|`kubectl describe deployment deployment-name`|to view the events of deployment|
|`kubectl rollout history deployment deployment-name`|Rollout history of deployment|
|`kubectl rollout history deployment deployment-name --revision=2`|view the actual changes in deployment|
|`kubectl rollout undo deployment deployment-name --to-revision=1`|undo the deployment changes and go back to the initial state|
|`kubectl rollout --help`|rollout Help|
|`kubectl scale deployment deployment-name --replicas=20`|manualy scale|
|`minikube ssh`|open minikube ssh terminal|
|`kubectl get configmaps`|list configmaps|
|`kubectl get secrets/secret`|list secrets|
|`kubectl get pv`|list persistent volumes|
|`kubectl get pvc`|list persistent volume claim|

- <b>POD Life Cycle Phase</b>
  - Pending : happens at that time of creation & made the entry(APIServer) in etcd
  - Running : means all the containers are created and shceduled at one of the worker node 
  - Succeeded : all the containers  are run successfull
  - Failed
  - Unknown : In the some reason, if the Pod status could not be obtained by the master node, API Server
  
- <b>Labels & Selector</b> : Part of the metadata, To logically group and queried the kubernetes resources
  - Labels are key-value pair assigned to a kubernetes cluster
  - use selector to filter out a specific pod from Tons of running pods
  
- <b>Annotations</b> Part of metadata, Any arbitrary and useful information read by the others developer or DevOps engineer & not directly used as labels.

- <b>Namespaces</b> A logical or virtual division of kubernetes cluster, each namespace allocate a quota ie cpu quota.

- <b>Deployment</b> Autoscaling feature

- <b>Three way to access Kubernetes cluster</b>
  - Web Dashboard : minikube dashboard
  - CLI kubectl command line
  - REST APIs
  
- <b>Service & Types</b>
  - Service is another abstraction just like a pod, it will logically group a set of pods that needs to access each other, so that they will communicate to each other.
  - Service, A logically grouping of pods that needs to each other or they can perform a functionality that required by a application out side the cluster.
  - Depending on different level of access, there are different service types we can configured.
    - Only Cluster
      - <b>ClusterIP Service</b>, default is ClusterIP if we not define type.
    - Outside
      - <b>NodePort Service</b> Port range 30000 to 32767, internally uses the ClusterIP
    - Maps to a entity
      - <b>LoadBalencer Service</b> used wrt cloud provider like AWS, Azure. Internally uses the ClusterIP & NodePort
    - External Name
    - Ingress lower number port like 8080, 80
    
  - <b>Rolling Update</b> strategies
    - Recreate - dosent promise zero down time there
    - RollingUpdate - promise zero downtime
      - maxUnavailable - (For old Pods)how many pods immediate destroy at the time of RollingUpdate
      - maxSurge - (For new Pods)how many new pods should create at the time of RollingUpdate
  - Labels and Selectors used to map Pods to Replicasets in a Deployment and Pods to a Service.
  - Service will open up the resources in the cluster to the world outside.
  
  - <b>Kubernetes Volume</b>
    - Types;
      - emptyDir
      - nfs
      - hostPath
      - Config Map & secret and many more
  - <b>Persistent Volume & PersistentVolumeClaim</b>
    - Persistent Volume is space at cluster level and it claim through PersistentVolumeClaim
      - Create persistent volume
      - create persistent colume claim
      - mount the volume claim
    - Access modes
      - ReadWriteOnce -> only one Node in cluster can Read & Write to the perticuler volume in the space of cluster
      - ReadOnlyMany -> Any number of Nodes in cluster can read only this volume 
      - ReadWriteMany -> Any number of Nodes in cluster can read and write this volume
  

- <b>Kubernetes Architecture</b>
![alt text](https://github.com/pratap86/microservices/blob/master/images/K8s_Architecture.PNG?raw=true)

- Kubernetes Installation
  - Single Node Installation
    - Minikube & Docker Desktop, master & worker nodes come together, good for development & testing
  - Single Master & Multi worker
  - Multi master & Multi Worker
  - Multi Master, Multi worker & multi etcd
    

## Apache Camel Framework with Spring Boot

|application.properties|Docker Service|
|---|---|
|`spring.activemq.broker-url=tcp://localhost:61616`|connect the microservice to ActiveMQ|
|`camel.component.kafka.brokers=localhost:9092`|connect the microservice to Kafka|

## Transactions in Microservices

### SAGA

- SAGA is a event handling component that manage the sequence of local transactions.
- SAGA handle an event and publish a new command to trigger the next local transaction in SAGA. If one of local transaction is fails than SAGA would need to execute a series of compensating transactions that will undo the changes that was made by preceding transactions.
- Compensating transactions are performed in reverse order.
- 

#### Choreography based SAGA
#### Orchestration based SAGA

### CQRS (Command Query Responsibility Segregation)
### Event Sourcing
### Axon Framework & Axon Server
