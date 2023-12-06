FROM openjdk:22-slim

WORKDIR /app

COPY . /app

EXPOSE 8080

CMD ["java", "-jar", "target/hello-spring-boot-0.0.1-SNAPSHOT.jar"]