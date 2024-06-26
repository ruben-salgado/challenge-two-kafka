FROM openjdk:17

WORKDIR /app

COPY target/challenge-two-kafka-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
