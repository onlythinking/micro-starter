FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
EXPOSE 8761
ADD registry-service.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker","-jar","/app.jar"]