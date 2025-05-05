# استخدم JDK رسمي
FROM openjdk:17-jdk-slim

# اضبط مجلد العمل
WORKDIR /app

# نسخ كل ملفات المشروع
COPY . .

# إعطاء صلاحية التنفيذ للملف mvnw
RUN chmod +x mvnw

# بناء المشروع بدون تشغيل الاختبارات
RUN ./mvnw clean package -DskipTests

# المرحلة النهائية
FROM openjdk:17-jdk-slim
WORKDIR /app

# نسخ ملف الجار من المرحلة السابقة
COPY --from=0 /app/target/*.jar app.jar

# البورت اللي التطبيق بيسمع عليه
EXPOSE 8080

# أمر التشغيل
ENTRYPOINT ["java","-jar","app.jar"]
