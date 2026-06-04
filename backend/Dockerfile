# backend/Dockerfile
# ============================================
# Estágio 1: Build da aplicação
# ============================================
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /build

# Copiar arquivos de dependência primeiro (melhor cache)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Baixar dependências (cacheável)
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Compilar a aplicação (sem testes para agilizar)
RUN mvn clean package -DskipTests

# ============================================
# Estágio 2: Desenvolvimento
# ============================================
FROM maven:3.9-eclipse-temurin-21-alpine AS development

WORKDIR /app

# Instalar ferramentas úteis para debug
RUN apk add --no-cache curl

# Copiar pom.xml primeiro
COPY pom.xml .

# Baixar dependências
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Expor portas
EXPOSE 8080 5005

# Comando para desenvolvimento com hot-reload e debug
CMD ["mvn", "spring-boot:run", \
     "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'", \
     "-Dspring.devtools.restart.enabled=true"]

# ============================================
# Estágio 3: Produção
# ============================================
FROM eclipse-temurin:21-jre-alpine AS production

WORKDIR /app

# Criar usuário não-root para segurança
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Instalar dependências de runtime (se necessário)
RUN apk add --no-cache curl tzdata

# Configurar timezone (ajuste conforme necessário)
ENV TZ=America/Sao_Paulo

# Copiar JAR do estágio de build
COPY --from=builder /build/target/app.jar app.jar

# Criar diretório para logs
RUN mkdir -p /app/logs && chown -R appuser:appgroup /app


USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1


EXPOSE 8080

ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", \
            "app.jar"]