from sourcemation/jdk-25

# Copy your Java application JAR file into the image
COPY your-application.jar /app/your-application.jar
# Set the working directory
WORKDIR /app
# Run the Java application
CMD ["java", "-jar", "your-application.jar"]
