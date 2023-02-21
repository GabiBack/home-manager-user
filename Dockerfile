FROM openjdk:17
EXPOSE 8080
ADD target/home-manager-user.jar home-manager-user.jar
ENTRYPOINT ["java","-jar","/home-manager-user.jar"]