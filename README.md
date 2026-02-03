**This repository contains a project I'm working on in my free time. I upload a new feature here every day.
Thank you for taking the time to check it out. Enjoy!**

(**_В этом репозитории собран проект, над которым я работаю в свободное время. Тут каждый день заливаю новую фичу.
Спасибо, что нашли время ознакомиться с ним. Приятного просмотра!_**)

**Bank Account Microservice** – a Java and Spring Boot backend API using modern development and containerization best practices.
(**Микросервис банковского счёта** — бэкенд api на Java и Spring Boot с использованием современных практик разработки и контейнеризации).

🛠 **Technology Stacks** (**Стек Технологий**)

**Основные технологии**

* **Language Version:** Java 21
* **Frameworks:** Spring Boot 3.x, Spring Cloud (Starter Bus AMQP, Cloud Config Monitor)
* **Lombok**
* **Spring Cloud Eureka Server**
* **Контейнеризация: Docker, Docker Compose**
* **Google Container Tools (jib) -** For creating optimized Docker and OCI images for Java applications (для создания оптимизированных образов Docker и OCI для Java приложений)
* **Hookdeck:** For testing a GitHub webhook (Для тестирования вебхука GitHub).
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA (Hibernate)
* **API Documention:** OpenAPI 3 (Swagger UI)
* **Kafka** - Pannned (запланировано). I learned a little bit about Kafka from this project. (_Я узнал немного о кафке в этом проекте:_ https://github.com/capfer27/spring-book-kafka-shop-api)
* **Spring Cloud Gateway** - Pannned (запланировано)
* **Grafana, Open Telemetry, Prometheus** - Pannned (запланировано)
* **Authentication:** OAuth - Pannned (запланировано)
* **Caching:** Spring Data Redis - Pannned (запланировано)
* **CI/CD** - запланировано
* **Kubernetes orchestration** - Pannned (запланировано)
* **Helm** - Pannned (запланировано)

**Testing (Тестирование)**

* JUnit 5, Mockito - Pannned (запланировано)
* Spring MVC Test -Pannned (запланировано)
* Testcontainers - Pannned (запланировано)

**Services (Сервисы)**

* Account Service - For bank account management (управление банковским счетом).
* Cards Service - For Card management (Управление картами).
* Loans Service - For Loan management (Управление кредитами).
* Config Service - For Configuration management (Управление конфигурации).
* Eureka Service - For Service discovery and registration (для обнаружения и регистрации сервисов).

🚀 **Project launch (Запуск проекта)**

**Prerequisites (Предварительные требования)**

* JDK 21 (17+ should be should be enough, but I haven't tested it (17+ должно быть достаточно - но не проверил))
* Docker 20.10+
* Docker Compose 2.4+
* Gradle 8.14+
* GIT 2.51+

**Running locally via docker (Локальный запуск через Docker Compose)**
1. Clone the repository (Клонируйте репозиторий):
   `git clone https://github.com/capfer27/spring-boot-microservices.git`
2. `cd docker-compose && prod `
3. Launch all services (Запустите все сервисы):
   `docker-compose up -d --build`

   - This command will create a shared PostgreSQL container for all services and run all microservices in separate containers (Эта команда создаст общий контейнер PostgreSQL для всех сервисов и запустит все микросервисы в отдельных контейнерах):
        * Accounts Service
        * Cards Service
        * Loans Service
        * Config Service

4. Stopping the services (Остановка сервисов): `docker-compose down`

5. В SWAGGER UI Services will be available at the following addresses (SWAGGER UI сервисы будут доступны по адресам):
     - Cards microservice URI: http://localhost:9000/swagger-ui/index.html#/
     - Accounts microservice URI: http://localhost:8080/swagger-ui/index.html#/
     - Loans microservice URI: http://localhost:8090/swagger-ui/index.html#/

**Скриншоты Rest UIs**
 - Cards:
  ![img.png](img.png)

 - Accounts:
  ![img_1.png](img_1.png)  

 - Loans: 
  ![img_2.png](img_2.png)  
