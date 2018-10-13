FROM maven:3.5.4-jdk-8-alpine AS build
WORKDIR /usr/src/app
COPY src ./src 
COPY pom.xml .
RUN mvn clean package

FROM openjdk:8-jre-alpine  
COPY --from=build /usr/src/app/target/backend-0.0.1-SNAPSHOT.jar /usr/app/backend-0.0.1-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/backend-0.0.1-SNAPSHOT.jar"]