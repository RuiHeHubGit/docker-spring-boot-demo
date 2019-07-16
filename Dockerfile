FROM openjdk:8u181-jdk-alpine

ARG NAME
ARG VERSION
ARG JAR_FILE

LABEL name=$NAME version=$VERSION

ENV TZ=Asia/Shanghai
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone

RUN set -eux; \
    addgroup --gid 1000 shdev; \
    adduser -S -u 1000 -g shdev -h /home/shdev/ -s /bin/sh -D shdev; \
    mkdir -p /home/shdev/logs /home/shdev/tmp; \
    su shdev; \
    chown -R shdev:shdev /home/shdev

WORKDIR /home/shdev/

COPY target/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

EXPOSE 8080

VOLUME tmp/
