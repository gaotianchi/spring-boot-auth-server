# 使用官方 OpenJDK 作为基础镜像
FROM openjdk:17-jdk-slim

LABEL authors="gaotianchi"

# 设置工作目录
WORKDIR /app

# 将构建的 Spring Boot JAR 文件复制到容器中
COPY target/auth-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用所需的端口，默认情况下 Spring Boot 使用 8080 端口
EXPOSE 8090

# 运行 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]

