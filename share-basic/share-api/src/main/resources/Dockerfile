FROM openjdk:8u312

ENV LUCAS_HOME=/app

RUN mkdir /app/

RUN mkdir -p /app/logs/share-api/

RUN mkdir -p /app/upload_tmp/

COPY target/share-api-1.0.0.jar /app/

RUN pwd && cd /app && ls

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/share-api-1.0.0.jar -noverify -Dspring.profiles.active=test"]

#ENTRYPOINT ["java -XX:MaxGCPauseMillis=200 \
#	-Xms512m -Xmx512m -Xss512k \
#	-Xlog:gc*:file=/app/logs/share-api/${HOSTNAME}_gc.log \
#	-XX:+HeapDumpOnOutOfMemoryError \
#	-XX:HeapDumpPath=/app/logs/share-api/${HOSTNAME}_heapdump.hprof \
#	-Dserver.port=8080 \
#	-Djava.io.tmpdir=/app/upload_tmp \
#	-jar /app/share-api-1.0.0.jar" ]


#	-Dlog4j2.formatMsgNoLookups=true \
#	-Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector \
#    -DAsyncLogger.RingBufferSize=40960 \
#	-Dlog.level.console=debug \
#	-Dlog.level.com.tal=debug \
