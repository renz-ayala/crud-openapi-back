FROM gcr.io/distroless/java21-debian12
LABEL authors="renz.ayala"

COPY ./consulta-reemplacamiento-ms-0.0.1-SNAPSHOT.jar ./ms-placas.jar
CMD ["ms-placas.jar"]
EXPOSE 10110
ENTRYPOINT ["java","-Dspring.profiles.active=local","-Duser.timezone=America/Lima","-jar","ms-placas.jar"]

## .\gradlew clean build -x test --no-daemon
## cp .\build\libs\consulta-reemplacamiento-ms-0.0.1-SNAPSHOT.jar  .\src\main\docker\
## cd .\src\main\docker\
## docker container stop ms-placas
## docker build . -t placas/ms-placas:latest
## docker run -d --rm --name ms-placas --env-file .env -p 10110:10110 placas/ms-placas:latest