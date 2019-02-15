FROM openjdk:8
ADD target/app-dian.jar app-dian.jar
EXPOSE 8081
ENTRYPOINT [ "java","-jar","app-dian.jar" ]
