# Etapa de Build
FROM maven:3.9.4-eclipse-temurin-21 AS BUILD

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Compila o projeto com Maven
RUN mvn clean install

# Etapa Final
FROM openjdk:21-jdk-slim

# Define a porta exposta
EXPOSE 8080

# Copia o artefato gerado para o contêiner final
COPY --from=BUILD /app/target/clientes-0.0.1-SNAPSHOT.jar app.jar

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
