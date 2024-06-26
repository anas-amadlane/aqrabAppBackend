FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src src

RUN ./mvnw package -DskipTests

EXPOSE 8080

CMD java -jar target/NvpProject-0.0.1-SNAPSHOT.jar
