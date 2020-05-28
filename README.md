# Online Shopping Center with Microservices Architecture
This project consists of a pure microservices architecture to implement a simple online shopping application using Spring Boot (2.3.0). During development, best-practice research is done for many details regarding microservices architecture, and applied them.

## Demo
The application demo can be accessible from [here]().
- Swagger documentation can be accessible from `localhost:9000/swagger-ui.html`

## Architecture
The applied architecture is visualized below.
![Alt text](docs/resources/architecture.png?raw=true "architecture")

## Technologies
- Eureka (as service registry and discovery)
- Zuul (as API gateway service)
- PostgreSQL (as data storage)
- RabbitMQ (for asynchronous communication)
- RestTemplate (for synchronous communication)
- Swagger2 (for REST API documentation)

## Sample usecase
A sample usecase scenario regarding adding a product to user's basket is given below.
![Alt text](docs/resources/usecase-add-product.png?raw=true "usecase-add-product")

## Other features
- Custom HTTP response structure is created and integrated to view action results in a naive way.
- Custom exceptions are implemented under `exceptions` package of each service.

## Future work
- More detailed log management.
- Unit testing implementation using Mocks.
- JWT token-based authentication entegration.
- Containerization using Docker.
- Other system requirements regarding real environment.

## Acknowledge
Thanks to [him](https://www.youtube.com/watch?v=y8IQb4ofjDo) for nice video series regarding understanding of microservices architecture.
Thanks to [him](https://github.com/DogukanZengin/SimpleMicroServiceArch) for configuration settings of RabbitMQ.

