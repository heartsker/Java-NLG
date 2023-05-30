FROM openjdk:19-alpine

RUN mkdir datasets
COPY datasets datasets
COPY target/*.jar medved.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/medved.jar"]