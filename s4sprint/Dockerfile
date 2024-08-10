FROM eclipse-temurin:22
WORKDIR /s4sprint
COPY target/s4sprint-1.0-SNAPSHOT.jar s4sprint.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "s4sprint.jar"]