FROM openjdk:8-jdk-alpine
LABEL maintainer="arbrim"
ADD target/polydo-0.0.1-SNAPSHOT.jar polydo
ENTRYPOINT ["java", "-jar","polydo"]
EXPOSE 8080
