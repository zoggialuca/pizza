#build stage
FROM gradle:jdk11 as build
WORKDIR /app
COPY . ./
RUN gradle build -x test

FROM openjdk:11.0.9-jre-slim-buster
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./spring-boot-application.jar
ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]