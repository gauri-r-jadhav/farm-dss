# Use the official image as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar to the container
COPY target/farm-0.0.1-SNAPSHOT.jar /app/farm.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "farm.jar"]
