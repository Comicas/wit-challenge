# WIT Challenge - Calculator REST API

A **Spring Boot** REST API that provides basic calculator operations with **Kafka** integration, logging, and request tracing capabilities.

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Building the Project](#building-the-project)
- [Running the Project](#running-the-project)
- [API Usage](#api-usage)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)


## Key Components

- **`rest.java`** - REST Controller handling HTTP requests
- **`calculator.java`** - Business logic for mathematical operations
- **`KafkaProducer.java`** - Publishes results to Kafka topics
- **`KafkaConsumer.java`** - Consumes messages from Kafka topics
- **`requestID.java`** - Filter for request tracking and MDC propagation


## 🔧 Prerequisites

Before running the project, ensure you have:

- **Java 21+** installed
- **Maven** installed
- **Docker** and **Docker Compose** installed


## 🔨 Building the Project

### 1. Clone the Repository

```bash
git clone https://github.com/Comicas/wit-challenge
cd wit-challenge
```

### 2. Build with Maven

```bash
# Clean and build the complete project
./mvnw clean install

## 🚀 Running the Project

### 1. Start Kafka Infrastructure

```bash
# Start Kafka broker in detached mode
docker-compose up -d

# Verify Kafka is running
docker ps
```

**Note:** Wait 10-15 seconds for Kafka to fully initialize before starting the application.

### 2. Start the Spring Boot Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

```


### 3. Verify Application is Running

The application will start on **http://localhost:8080**

Check the logs for successful startup:

```
INFO  c.r.w.WitChallengeApplication - Started WitChallengeApplication
INFO  c.r.w.KafkaProducer - KafkaProducer initialized successfully
```


## 📡 API Usage

### Available Endpoints

| Method | Endpoint | Parameters | Description |
| :-- | :-- | :-- | :-- |
| GET | `/sum` | `a`, `b` | Addition operation |
| GET | `/subtract` | `a`, `b` | Subtraction operation |
| GET | `/multiply` | `a`, `b` | Multiplication operation |
| GET | `/divide` | `a`, `b` | Division operation |

### Example Requests

**Addition:**

```bash
curl "http://localhost:8080/sum?a=5&b=3"
```

```json
{"result": 8}
```


**Division by Zero:**

```bash
curl "http://localhost:8080/divide?a=10&b=0"
```

```json
{"error": "Division by zero not allowed"}
```


### Request Headers

Each response includes a unique request identifier:

```
X-Request-ID: f47ac10b-58cc-4372-a567-0e02b2c3d479
```


## 🧪 Testing

### Run All Tests

```bash
./mvnw test
```


### Test Coverage

The project includes comprehensive tests for:

- **Unit Tests** - `calculatorTest.java`
- **Integration Tests** - `restTest.java`
- **Mock Testing** - Kafka producer mocking


### Manual Testing

```bash
# Test all operations
curl "http://localhost:8080/sum?a=1.5&b=2.5"
curl "http://localhost:8080/subtract?a=10&b=4"
curl "http://localhost:8080/multiply?a=6&b=7"
curl "http://localhost:8080/divide?a=15&b=3"

# Test error cases
curl "http://localhost:8080/divide?a=10&b=0"
curl "http://localhost:8080/sum?a=invalid&b=5"
```


### Sample Log Output

```log
2025-09-09 14:07:15.123 [http-nio-8080-exec-1] INFO [f47ac10b-58cc] c.r.w.requestID - Generated Request-ID for GET /sum
2025-09-09 14:07:15.124 [http-nio-8080-exec-1] INFO [f47ac10b-58cc] c.r.w.rest - Received sum request: a=5, b=3
2025-09-09 14:07:15.125 [http-nio-8080-exec-1] DEBUG [f47ac10b-58cc] c.r.w.calculator - Sum result: 8
2025-09-09 14:07:15.126 [http-nio-8080-exec-1] INFO [f47ac10b-58cc] c.r.w.KafkaProducer - Message sent successfully to topic 'result': 8
```

## 🛠 Troubleshooting

### Common Issues

**1. Kafka Connection Error**

```
WARN NetworkClient - Connection to node -1 could not be established
```

**Solution:** Wait 10-15 seconds for Kafka to fully start before launching the application.