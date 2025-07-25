# WattWise Dashboard

This project contains a Spring Boot backend (`ESB-APP`) and a lightweight web
frontend (`frontend`). The frontend serves a simple dashboard for viewing tasks,
rewards and leaderboards.

## Prerequisites
- Node.js 18+
- Java 17+
- MySQL running locally (see `ESB-APP/src/main/resources/application.properties`)

## Running the Backend
From the `ESB-APP` directory:

```bash
./mvnw spring-boot:run
```

The backend listens on `http://localhost:9192`.

## Running the Frontend
Run these commands from the repository root:

```bash
npm install     # installs frontend dependencies
npm start       # serves frontend on http://localhost:3000
```

The root `npm` scripts simply forward to the `frontend` directory.

## Testing
Automated tests can be run for the backend with:

```bash
./mvnw test
```

Note that Maven may fail to resolve dependencies without Internet access.
