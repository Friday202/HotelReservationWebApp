# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/inventory-service-1.0-SNAPSHOT.jar /app/inventory-service-1.0-SNAPSHOT.jar

# Specify the environment variables without defaults
ENV DATABASE_HOST=localhost
ENV DATABASE_PORT=5432
ENV DATABASE_NAME=mydatabase
ENV DATABASE_USERNAME=defaultuser
ENV DATABASE_PASSWORD=defaultpassword

# Expose the port that the application will run on
EXPOSE 8081

# Run the JAR file
CMD ["java", "-jar", "inventory-service-1.0-SNAPSHOT.jar"]
