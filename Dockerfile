# Etapa 1:Build con Gradle 9.5.1 y JDK 21 (Compilación)
from gradle:9.5.1-jdk21 as build
copy --chown=gradle . /app
workdir /app
run gradle bootJar --no-daemon

# Etapa 2: Runtime con JDK 21 (Ejecución)
from eclipse-temurin:21-jdk
workdir /app
copy --from=build /app/build/libs/*.jar platzi_play.jar
expose 8080
entrypoint ["java", "-Dspring.profiles.active=prod", "-jar", "platzi_play.jar"]