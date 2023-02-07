#
# Build stage
#
FROM maven:3.8.6-amazoncorretto-17 AS MAVEN
COPY pom.xml /build/app/
COPY src /build/app/src/
WORKDIR /build/app/
RUN mvn clean package -DskipTests -Dspring-boot.run.profiles=container

#
# Package stage
#
FROM amazoncorretto:17
ENV SPRING_PROFILES_ACTIVE=container
EXPOSE 3001
COPY --from=MAVEN /build/app/target/spart-0.0.1-SNAPSHOT.jar spart.jar

RUN sh -c 'touch /spart.jar'

ENTRYPOINT ["java","-jar","/spart.jar"]