FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY target/MoneyTransfer-0.0.1.jar MoneyTransfer.jar

CMD ["java", "-jar", "MoneyTransfer.jar"]