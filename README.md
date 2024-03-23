# keycloak-angular-spring-in-docker

This is a training repository to understand the used technologies.

If you wanna start the application you should do the following steps.

cd example/ExampleService
mvn clean package
docker build -t example-service:latest .
cd ..
cd frontend
docker build -t frontend-service:latest .
cd ..
cd docker
docker-compose up -d

log into localhost:8080 with User "admin" and Password "admin"
create a new user

open the browser navigate to localhost:4200
login with the created user
try to query the spring-boot service and the logout with the buttons