# IDR Rate Aggregator – Spring Boot

This project is a take-home test solution for the **Allo Bank Backend Developer** position.  
The application exposes a single polymorphic REST endpoint that aggregates multiple finance-related resources from the public **Frankfurter Exchange Rate API**, with a focus on **Indonesian Rupiah (IDR)** data.

---

## ✨ Features

- Single polymorphic REST endpoint
- Strategy Pattern for dynamic resource handling
- External API client created using Spring `FactoryBean`
- Data fetched **once at application startup**
- Thread-safe and immutable in-memory data store
- Personalized USD buy spread calculation
- Unit tests and integration tests

---

## 🚀 How to Run the Application

### Prerequisites
- Java 17+
- Maven Wrapper (included)

### Run Application
```bash
./mvnw spring-boot:run
