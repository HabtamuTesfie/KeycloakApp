FROM openjdk:11-jre-slim-stretch

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""

RUN adduser -D -S /bin/sh jhipster
WORKDIR home/jhipster

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhispter

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

ADD *.war app.wa
