FROM openjdk:8u312

ENV LUCAS_HOME=/app

RUN mkdir /app/

RUN mkdir -p /app/logs/share-app-im/

RUN mkdir -p /app/upload_tmp/

COPY target/share-app-im-1.0.0.jar /app/

RUN pwd && cd /app && ls

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/share-app-im-1.0.0.jar"]
