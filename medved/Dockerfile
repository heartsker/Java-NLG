FROM openjdk:19-alpine

ARG DATASET
ARG MAXLENGTH
ARG DEPTH

ENV ENV_DATASET=${DATASET}
ENV ENV_MAXLENGTH=${MAXLENGTH}
ENV ENV_DEPTH=${DEPTH}

RUN mkdir datasets
COPY datasets datasets
COPY target/*.jar medved.jar

ENTRYPOINT java -Ddataset=$ENV_DATASET -Dmaxlength=$ENV_MAXLENGTH -Ddepth=$ENV_DEPTH -jar /medved.jar $0