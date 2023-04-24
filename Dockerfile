FROM openjdk:17
#RUN apt-get update && apt-get install -y bash
#ENV ACTIVEMQ_BROKER_URL="tcp://activemq-container:61616"
#COPY target/home-manager-user.jar /app/home-manager-user.jar
#COPY --from=activemq-image /opt/activemq /opt/activemq
#WORKDIR /app
EXPOSE 8080
ADD target/home-manager-user.jar home-manager-user.jar
ENTRYPOINT ["java","-jar","/home-manager-user.jar"]