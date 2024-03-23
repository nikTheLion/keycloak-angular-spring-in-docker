FROM eclipse-temurin:17-jdk-alpine
WORKDIR .
COPY target/example-service.jar ./example-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","./example-service.jar"]