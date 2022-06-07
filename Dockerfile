FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY build/libs/currency-exchange-service-0.0.1-SNAPSHOT.jar currency-exchange-service.jar
EXPOSE 8080
RUN bash -c 'touch /currency-exchange-service.jar'
ENTRYPOINT ["java","-jar","/currency-exchange-service.jar"]
CMD ["-start"]
