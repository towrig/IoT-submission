# 1oT Submission by Karl Kuusik

## Modules
* `weather-api` - Spring Boot application serving data from a weather API.
* `weather-frontend` - React + Vite frontend with redux-saga state management.

## Quick guide
Before running the following commands, make sure you are running Java 17+ and Node.JS 18+-.
1. Run the backend server:
```
gradlew build
gradlew bootRun
```
2. Run the frontend:
```
cd weather-frontend
npm install
npm start
```
3. The frontend should open automatically in your browser, if it doesnt, navigate to `localhost:3000`.