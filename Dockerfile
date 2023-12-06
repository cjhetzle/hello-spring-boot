FROM eclipse-temurin:17.0.9_9-jre

WORKDIR /app

COPY . /app

EXPOSE 8080

CMD ["java", "-jar", "target/hello-spring-boot-0.0.1-SNAPSHOT.jar"]