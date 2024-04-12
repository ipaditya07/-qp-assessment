# Use a base image with Java and Maven installed
FROM maven:3.8.1-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

# Build the application using Maven
RUN mvn package -DskipTests

# Use a lightweight base image with Java only
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/*.jar demo.jar

# Expose the port that the application runs on
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "demo.jar"]
