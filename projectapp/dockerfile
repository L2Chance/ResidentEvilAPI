# Usa una imagen base ligera de JRE para la ejecución de la aplicación.
# 'eclipse-temurin:21-jre-alpine' es perfecta para Java 21.
FROM eclipse-temurin:21-jre-alpine

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado a la imagen del contenedor.
# El nombre del archivo se basa en tu pom.xml: <artifactId>-<version>.jar
COPY target/projectapp-0.0.1-SNAPSHOT.jar app.jar

# Define el puerto que tu aplicación escuchará. Render lo usará automáticamente.
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "app.jar"]