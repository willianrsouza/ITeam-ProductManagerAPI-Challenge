FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY infrastructure/build/libs/*.jar app.jar
CMD ["java", "-jar", "/app/app.jar"]