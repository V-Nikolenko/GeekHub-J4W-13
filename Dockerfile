FROM gradle:jdk17 AS build
WORKDIR /app-source
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY Coursework ./Coursework
RUN ./gradlew clean Coursework:bootJar

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app-source/Coursework/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
