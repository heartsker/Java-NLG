FROM openjdk:19-alpine

COPY file.txt .
COPY target/*.jar medved.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/medved.jar"]