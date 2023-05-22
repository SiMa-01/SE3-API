# Verwenden Sie ein OpenJDK-Base-Image für
FROM openjdk:11-jdk

# Setzen Sie das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopieren Sie die Gradle-Konfigurationsdateien
COPY . .

# Kopieren Sie den Quellcode
COPY src ./src

# Bauen Sie die Anwendung mit Gradle
RUN ./gradlew build

# Kopieren Sie das Spring Boot JAR-Paket in den Container
COPY build/libs/cocktailapi-0.0.1-SNAPSHOT-plain.jar app.jar

# Definieren Sie den Befehl, der beim Start des Containers ausgeführt wird
CMD ["java", "-jar", "app.jar"]
