FROM openjdk:8-jre-alpine
ARG JAR_FILE=app.jar
ENV JARFILEENV=/$JAR_FILE
VOLUME /tmp
ADD target/$JARFILE /$JARFILE

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -XX:MaxHeapFreeRatio=50 -jar $JARFILEENV"]
