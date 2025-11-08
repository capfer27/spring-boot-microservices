FROM eclipse-temurin:21-jdk-alpine
LABEL maintainer="capfer27@gmail.com"
LABEL version="0.0.1-SNAPSHOT"
LABEL description="Spring Boot Accounts Microservice Application"
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]