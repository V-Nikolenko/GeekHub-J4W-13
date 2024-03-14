FROM openjdk:17
CMD ["./gradlew", "clean", "bootJar"]
COPY ./Coursework/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
