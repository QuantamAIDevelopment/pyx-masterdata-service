# PYX Marketplace Masterdata

A Spring Boot backend service for managing agent master data in the PYX Marketplace. Supports CRUD operations, robust DTO mapping, and is ready for deployment to Azure Web App.

## Features
- Agent information management (CRUD)
- Pagination and filtering
- Detailed agent card API for frontend
- Dynamic CORS configuration per environment
- Swagger/OpenAPI documentation
- Azure Web App deployment ready (CI/CD)

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Azure account (for deployment)
- SQL Server (Azure SQL or local)

### Local Development
1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd pyx-masterdata
   ```
2. Create `src/main/resources/application-local.yml` (see `application-dev.yml` for reference). Example:
   ```yaml
   server:
     port: 8080
   spring:
     datasource:
       url: jdbc:sqlserver://localhost:1433;database=pyx;encrypt=false
       username: sa
       password: yourpassword
       driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   cors:
     allowed-origins: "http://localhost:5173,http://localhost:3000"
     allowed-methods: "GET,POST,PUT,PATCH,DELETE,OPTIONS"
     allowed-headers: "*"
     allow-credentials: true
   ```
3. Run the app:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=local
   ```

### API Documentation
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Azure Deployment
- Use the provided GitHub Actions workflow or Azure Pipelines for CI/CD.
- Configure your Azure SQL and Web App settings in `application-dev.yml` or as environment variables/secrets.
- Update `SwaggerConfig.java` with your Azure Web App URL.

## Environment Profiles
- `local`: For local development (ignored by git)
- `dev`: For Azure dev deployment
- `prod`: For production

## Security
- Do not commit `application-local.yml` or secrets to version control.
- Use Azure Key Vault or pipeline secrets for production credentials.

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0) 