FROM eclipse-temurin:17-jre
WORKDIR /app
COPY build/libs/*-runner.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
