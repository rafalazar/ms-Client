FROM openjdk:8
VOLUME /tmp
EXPOSE 8103
ADD target/service-Client-0.0.1-SNAPSHOT.jar msclient.jar
ENTRYPOINT ["java","-jar","msclient.jar"]