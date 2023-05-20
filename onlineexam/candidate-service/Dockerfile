FROM openjdk:17
VOLUME /tmp
ADD target/candidate-service.jar candidate-service.jar
ENTRYPOINT [ "java","-jar","/candidate-service.jar" ]