# ============================================
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /build

# Copiar arquivos de dependência primeiro (melhor cache)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

# ============================================
FROM maven:3.9-eclipse-temurin-21-alpine AS development

WORKDIR /app

RUN apk add --no-cache curl

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

EXPOSE 8080 5005

CMD ["mvn", "spring-boot:run", \
     "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'", \
     "-Dspring.devtools.restart.enabled=true"]

#==============================
FROM eclipse-temurin:21-jre-alpine AS production

WORKDIR /api

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

RUN apk add --no-cache curl tzdata

ENV TZ=America/Sao_Paulo

COPY --from=builder /build/target/*.jar app.jar

RUN mkdir -p /api/logs && chown -R appuser:appgroup /api

USER appuser

HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1


EXPOSE 8080

ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", \
            "app.jar"]
