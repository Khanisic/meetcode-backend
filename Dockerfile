# 1. Build Stage: use a Gradle image with JDK 24
FROM gradle:8.14.0-jdk-21-and-24 AS builder  
WORKDIR /home/gradle/project

# Copy necessary files for Gradle build
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY src src

# Ensure the wrapper is executable
RUN chmod +x gradlew

# Build the fat JAR (adjust if using a different task or plugin)
RUN ./gradlew clean bootJar --no-daemon

# 2. Runtime Stage: use a slim Alpine image with Temurin JDK 24
FROM eclipse-temurin:24-jdk-alpine  
EXPOSE 8081

# Copy the built JAR from the builder stage
COPY --from=builder /home/gradle/project/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
