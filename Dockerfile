FROM gradle:jdk10 as builder

COPY --chown=gradle:gradle ./build.gradle /home/gradle/
WORKDIR /home/gradle/
RUN gradle resolveDependencies

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle jar

FROM openjdk:10-jre-slim
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs /app/
WORKDIR /app
CMD java -jar demo-0.0.1-SNAPSHOT.jar