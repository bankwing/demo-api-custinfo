FROM openjdk:8-jre-alpine
ARG JARFILE=app.jar
ENV JARFILEENV=/$JARFILE
VOLUME /tmp
ADD target/$JARFILE /$JARFILE

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -XX:MaxHeapFreeRatio=50 -jar $JARFILEENV"]