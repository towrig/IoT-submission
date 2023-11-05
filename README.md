# 1oT Submission by Karl Kuusik

## Modules
* `weather-api` - Spring Boot application serving data from a weather API.
* `weather-frontend` - React + Vite frontend with redux-saga state management.

## Quick guide
Before running the following commands, make sure you are running Java 17.

* Run frontend and backend in parallel: 
```
gradlew build
gradlew bootRun start --parallel
```
* If that fails for some reason, you can run the processes separately 
(though you will additionally need Node v18 for this to work):

Terminal 1:
```
gradlew build
gradlew bootRun
```

Terminal 2:
```
cd weather-frontend
npm install
npm start
```

Note: In both cases, the frontend should open automatically in your browser,
if it doesn't, navigate to `localhost:3000`.