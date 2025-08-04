# =========================================================================
# === STAGE 1: COMPILACIÓN ===
# =========================================================================

# Usamos una imagen de JDK 21 (Temurin es una opción oficial de OpenJDK)
# para compilar el código. 'jdk-alpine' es una versión ligera.
FROM eclipse-temurin:21-jdk-alpine AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Maven (pom.xml) para descargar dependencias
COPY pom.xml .

# Copia el código fuente del proyecto
COPY src ./src

# Copia la configuración de Maven si existe
# COPY .mvn .mvn

# Compila el proyecto con Maven y salta las pruebas
# El resultado será el archivo JAR en el directorio 'target/'
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# =========================================================================
# === STAGE 2: IMAGEN FINAL PARA EJECUCIÓN ===
# =========================================================================

# Usamos una imagen de JRE 21 (más pequeña que el JDK) para ejecutar la aplicación
FROM eclipse-temurin:21-jre-alpine

# Establece el directorio de trabajo dentro del contenedor final
WORKDIR /app

# Copia el archivo JAR compilado desde el stage 'builder' a esta imagen
# Ten en cuenta que el nombre del JAR se basa en tu pom.xml: projectapp-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/target/projectapp-0.0.1-SNAPSHOT.jar app.jar

# Define el puerto por el que escuchará tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "app.jar"]
