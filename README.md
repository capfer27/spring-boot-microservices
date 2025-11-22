The Strangler Fig Pattern
 - The strangler fig pattern is a software design pattern used to gradually replace
   or refactor a legacy system with a new system, piece by piece, without disrupting the 
   existing functionality. This pattern gets its name from the way a strangler fig plant
   grows around an existing tree, slowly replacing it until the original tree is no longer
   needed.

   takeaways:
   - When to use The Strangler Fig Pattern:
     When you need to modernize a large or complex legacy system.
     When you want to avoid the risk associated with a complete system rewrite or "big bang" migration.
     When the legacy system needs to remain operational during the transition to the new system.


Docker Fundamentals
 - A docker container is a loosely isolated environment that allows us to build and run
   software packages. These software packages include the code and all dependencies to run
   the applications quickly and reliably on any computing environment. We call these packages
   as container images.


How to generate docker images
 - There several ways to generate docker images:
   Using the traditional Dockerfile
   Using Buildpacks
   Using Google Jib

Running the jar file command:
 - java -jar build/libs/accounts-0.0.1-SNAPSHOT.jar

Building docker images commands
 - docker build . -t caito21/accounts:v0.0.1  ( -t stands for tag)
 - docker inspect image 15727fc9fd1c (inspect the image)

Build or Start docker container based on the image
 - docker run -p 8080:8080 caito21/accounts:v0.0.1

Port Mapping | Port Forwarding | Port Publishing
 - docker run -p 8080:8080

Buildpack use something called builders.
The builders are design to analise and build the application.
 - Buildpacks set
 - Stack
 - Lifecycle manager - assemble everything into a file (container image)
Buildpacks Lifecycle:
 - Detect - detect language or framework
 - Build - Fetch dependencies, compile code and configure runtime.

The most common docker commands:
 - docker images -> list all docker images present in the Docker Server
 - docker ps -> display or show all running containers
 - docker ps -a -> show all containers including running and stopped
 - docker image rm <image id>
 - docker image inspect <image id> -> display detailed imaged information
 - docker build . -t <image name> -> generate docker image based on Dockerfile settings
 - docker run -p [hostPort:ContainerPort] <image name> -> start a docker container based on a given image
 - docker container start <container id>
 - docker container stop <container id>
 - docker container pause <container id>
 - docker container unpause <container id>
 - docker container kill <container id>
 - docker container inspect <container id>
 - docker container restart <container id>
 - docker container logs <container id>
 - docker container logs -f <container id>
 - docker rm <container id>
 - docker container prune -> remove all stopped containers
 - docker image push [container_registry/username:tag] -> to push an image from a container registry
 - docker image pull [container_registry/username:tag] -> to pull an image from a container registry
 - docker image prune -> to remove all unused images
 - docker container stats
 - docker system prune [--all] -> remove stopped containers, dangling images, and unused networks, volumes and cache.
 - docker rmi <image id>
 - docker login -u <username>
 - docker logout -> log out from docker hub container registry
 - docker history <image name>
 - docker exec -it <container id> sh
 - docker compose up [-d]
 - docker compose down

The 15-Factor methodology
 1. One codebase, one application
 2. API First
 3. Dependency Management
 4. Design, Build, Release, Run.
 5. Configuration, Credentials & Code
 6. Logs
 7. Disposability
 8. Backing Services
 9. Environment Parity 
10. Administrative Processes
11. Port Binding
12. Stateless Process
13. Concurrency
14. Telemetry
15. Authentication & Authorization

Spring Profiles:
 - Set up profiles via command line arguments (Program arguments)
   *  --spring.profiles.active=prod --build.version=1.1
 - Configure spring profiles via VM Options
   * -Dspring.profiles.active=prod -Dbuild.version=1.1
 - Configure spring profiles via Environment Variables
   * SPRING.PROFILES.ACTIVE=prod;BUILD.VERSION=1.4;

Steps to Refresh Config Files With Spring Actuator:
 - Add Spring Boot Actuator Dependency In the config client dependencies
 - Expose the endpoint in the application.yaml file: 
    management:
        endpoints:
            web:
             exposure:
                include: refresh           
 - Enable Refresh API from refresh endpoints: For instance localhost:8080/actuator/refresh

Spring Cloud Bus - links nodes of a distributed system with a lightweight message broker (AMQP or Kafka).
 - RabbitMQ docker installation command:
   * docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
 - Expose the endpoint in the application.yaml file:
     management:
        endpoints:
           web:
            exposure:
               include: busrefresh
 - Do the config changes and refresh all of them that are registered with rabbitmq at once, via endpoint: 
   * http://localhost:8080/actuator/busrefresh

- Spring Cloud Config Monitor 
  * When changes occur on GitHub fire a webhook to handle data change events on Spring Bus to refresh everything ...
- Install the hookdeck from website https://console.hookdeck.com/
- brew install hookdeck/hookdeck/hookdeck
- Login and start the CLI with those commands: hookdeck login --cli-key 2e4wkp8n48zocgmpgvxlyvaqj4k80g8qqubixrckoa1va6asnu
- hookdeck listen [the port of config server] Source: hookdeck listen 8071 Source --cli-path /monitor
- hookdeck logout - in case of login issues