from sourcemation/jdk-25

# Copy your Java application JAR file into the image
COPY  target/cli-0.1.0.jar /app/cli-0.1.0.jar
# Set the working directory
WORKDIR /app
# Run the Java application
CMD ["java", "-jar", "cli-0.1.0.jar"]
