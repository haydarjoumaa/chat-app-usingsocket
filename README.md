# chat-app-usingsocket

This project is now a Spring Boot WebSocket chat server with a small HTTP API and an optional web client. The original socket-based classes remain in the `tcp/` directory for reference, but the recommended way to run the application is via Spring Boot.

## Building

Use Maven to build the project:

```bash
mvn package
```

## Running

Launch the Spring Boot application with:

```bash
mvn spring-boot:run
```

The WebSocket endpoint is available at `/chat` and messages are broadcast on the `/topic/messages` destination. A small web client is served from the application root so you can test the chat in a browser.

Clients can connect using any STOMP/WebSocket client or send messages via a REST call:

```bash
curl -X POST -H 'Content-Type: application/json' \
     -d '{"from":"bob","content":"hello"}' http://localhost:8080/send
```

## Chat History

The server retains the last 100 messages in memory, each tagged with a timestamp. You can retrieve this history via a simple HTTP GET request:

```bash
curl http://localhost:8080/history
```

## Presence API

Clients can register themselves with the server so others know who is online. The server broadcasts the user list on `/topic/users` whenever it changes.

Join the chat:

```bash
curl -X POST -H 'Content-Type: application/json' \
     -d '{"name":"bob"}' http://localhost:8080/join
```

Leave the chat:

```bash
curl -X POST -H 'Content-Type: application/json' \
     -d '{"name":"bob"}' http://localhost:8080/leave
```

List current users:

```bash
curl http://localhost:8080/users
```
