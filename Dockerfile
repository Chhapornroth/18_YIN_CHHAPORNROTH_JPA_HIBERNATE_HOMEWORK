FROM gradle:jdk21-ubi-minimal AS build

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21.0.7_6-jre-ubi9-minimal

LABEL authors="Y.CHHAPORNROTH"

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]