FROM openjdk:11-jre-slim
ADD target/ft-sample-client-02.jar ft-sample-client-02.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ft-sample-client-02.jar"]
