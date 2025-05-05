# Use a base Java image with Maven pre-installed
FROM maven:3.9.5-eclipse-temurin-17 as build

WORKDIR /app

# Copy everything into the image (including mvnw + .mvn)
COPY . .

# Run Maven clean package
RUN ./mvnw clean package

# ---- Second stage ----
FROM openjdk:17-jdk-slim

VOLUME /tmp

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
