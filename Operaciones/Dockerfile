FROM openjdk:jdk-11.0.2
RUN apt-get update && apt-get install bash
RUN mkdir -p /usr/app/
ENV PROJECT_HOME /usr/app/
COPY build/libs/demo-0.0.1-SNAPSHOT.jar $PROJECT_HOME/demo-0.0.1-SNAPSHOT.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-jar", "./demo-0.0.1-SNAPSHOT.jar"]