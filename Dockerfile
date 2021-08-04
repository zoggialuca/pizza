#build stage
FROM gradle:jdk11 as build
WORKDIR /app
COPY . ./
RUN gradle build -x test

FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./spring-boot-application.jar
ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]