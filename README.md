# keycloak-angular-spring-in-docker

This is a training repository to understand the used technologies.

If you wanna start the application you should do the following steps.

1. cd example/ExampleService
2. mvn clean package
3. docker build -t example-service:latest .
4. cd ..
5. cd frontend
6. docker build -t frontend-service:latest .
7. cd ..
8. cd docker
9.  docker-compose up -d
10.  log into localhost:8080 with User "admin" and Password "admin"
11.  create a new user
12.  open the browser navigate to localhost:4200
13.  login with the created user
14.  try to query the spring-boot service and the logout with the buttons
