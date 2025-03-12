FROM openjdk:17
WORKDIR /app
COPY /target/ictaktest-0.0.1-SNAPSHOT.jar ictaktest.jar
EXPOSE 9096
ENTRYPOINT ["java", "-jar", "ictaktest.jar"]

