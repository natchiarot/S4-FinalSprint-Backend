FROM eclipse-temurin:22
WORKDIR /s4sprint
COPY target/s4sprint-1.0-SNAPSHOT.jar s4sprint.jar
EXPOSE 8080
ENV DB_URL=
ENTRYPOINT ["java", "-jar", "s4sprint.jar"]