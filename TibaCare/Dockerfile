# استخدم صورة OpenJDK علشان تشغل جافا
FROM openjdk:17-jdk-slim

# تعيين working directory داخل الحاوية
WORKDIR /app

# نسخ الملف jar اللي المشروع هيطلعه بعد الـ build
COPY target/*.jar app.jar

# أمر تشغيل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
