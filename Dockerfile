# Usar una imagen base con JDK 17 y Maven
FROM maven:3.8.4-openjdk-17 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package

# Crear una nueva imagen basada en AdoptOpenJDK 17
FROM openjdk:17

# Exponer el puerto que utilizará la aplicación
EXPOSE 8090

# Copiar el archivo JAR construido desde la etapa anterior
COPY "./Hotel-Alura-Api.jar" "./Hotel-Alura-Api.jar"

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/Hotel-Alura-Api.jar"]
