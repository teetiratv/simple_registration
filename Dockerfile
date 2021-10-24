FROM openjdk:17.0.1-slim as build
WORKDIR /build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x ./mvnw
COPY src src
RUN ./mvnw package -DskipTests
COPY target/*.jar app.jar

FROM openjdk:17.0.1-slim as production
WORKDIR /app
COPY --from=build /build/*.jar .
ENTRYPOINT ["java","-jar","/app/app.jar"]