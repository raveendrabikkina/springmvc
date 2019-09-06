FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/springmvc-*.jar springmvc.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springmvc.jar"]