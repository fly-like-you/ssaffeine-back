# Build stage
FROM eclipse-temurin:21-jdk AS TEMP_BUILD_IMAGE

# Application directory setup
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copy Gradle files and run the build
COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle/

# Grant execute permission for the Gradle wrapper script
RUN chmod +x gradlew

# Copy the rest of the application files and build the project
COPY . .
RUN ./gradlew -x test build

# Runtime stage
FROM eclipse-temurin:21-jre

# Set environment variables
ENV ARTIFACT_NAME=ssaffeineProject.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copy the built artifact from the build stage
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/ssaffeineProject.jar .

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "ssaffeineProject.jar"]
