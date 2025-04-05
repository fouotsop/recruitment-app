FROM openjdk:17-alpine

WORKDIR /workspace/app

COPY mvnw .

COPY .mvn .mvn

COPY pom.xml .

COPY src src

RUN ./mvnw install -DskipTests

RUN ls -l target

ENV DB_URL="jdbc:mysql://localhost:3306/candidate_evaluation_db"

RUN mv target/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]