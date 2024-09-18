## Use the official image as a parent image
#FROM openjdk:21-jdk-slim
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the built jar to the container
#COPY target/farm-0.0.1-SNAPSHOT.jar /app/farm.jar
#
## Expose port 8080
#EXPOSE 9001
#
## Run the jar file
#ENTRYPOINT ["java", "-jar", "farm.jar"]




# the base image
FROM openjdk:21-jdk-slim

# the JAR file path
ARG JAR_FILE=target/farm-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} application.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]