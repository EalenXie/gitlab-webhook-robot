FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD target/gitlab-webhook-robot-1.0.jar app.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx256m","-Xms256m","-Xss512k","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


# docker run -d -m 512m --network=host --restart=always -v /opt/logs/:/opt/logs -v /etc/localtime:/etc/localtime gitlab-webhook-robot --spring.profiles.active=dev