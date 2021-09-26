FROM openjdk:8-jdk-alpine
EXPOSE 8765
ADD target/api-gateway-server.jar api-gateway-server.jar
ENTRYPOINT ["java","-jar","/api-gateway-server.jar"]