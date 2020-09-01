FROM openjdk:11-jre-slim
ADD target/ft-sample-client.jar ft-sample-client.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ft-sample-client.jar"]
