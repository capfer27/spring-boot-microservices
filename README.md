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