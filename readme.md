## Startup Commands

cd backend + docker-compose up

cd backend + mvn spring-boot:run

cd frontend + node app.js

Load Frontend - http://localhost:3008

Post Data to API Endpoint - http://localhost:8080

{"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP", "amountSell": 1000, "amountBuy": 6107.10, "rate": 0.7471, "timePlaced" : "24-JAN-15 10:27:44", "originatingCountry" : "FR"}


## Architecture

API Request --> Order Endpoint --> RabbitMQ (order-processing-queue) --> Processor / Listener --> RabbitMQ (processed-trades-queue) <-- NodeJS Frontend (Broadcasts) <-- Frontend (React / Redux)

## Tech Used

Java Spring

NodeJs (Express)

React / Redux

RabbitMQ

SocketIO

## Queues
order-processing-queue

processed-trades-queue
