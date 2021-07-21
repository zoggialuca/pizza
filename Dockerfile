FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY build/libs/*.jar spring-boot-application.jar
ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]