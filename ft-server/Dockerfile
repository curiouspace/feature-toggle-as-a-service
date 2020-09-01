FROM openjdk:11-jre-slim
ADD target/ft-server.jar ft-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ft-server.jar"]
