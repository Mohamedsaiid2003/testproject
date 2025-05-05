# ======================
# Step 1: Build with Maven
# ======================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# انسخ ملفات المشروع و dependencies
COPY pom.xml .
COPY src ./src

# شغّل build بدون tests
RUN mvn clean package -DskipTests

# ======================
# Step 2: Run the jar
# ======================
FROM eclipse-temurin:17-jre

WORKDIR /app

# انسخ ملف jar اللي طلع من الـ build stage
COPY --from=build /app/target/*.jar app.jar

# شغّل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
