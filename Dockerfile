FROM maven:3.6.3-jdk-8 as build

COPY src /home/app/src

COPY pom.xml /home/app

RUN  mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:8-jre

RUN wget https://download.jboss.org/wildfly/20.0.0.Final/wildfly-20.0.0.Final.zip -q -O /opt/wildfly.zip

RUN unzip /opt/wildfly.zip -d /opt/

COPY --from=build /home/app/target/shoppingapi.war /opt/wildfly-20.0.0.Final/standalone/deployments/

RUN echo "America/Sao_Paulo"  > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

EXPOSE 8080

CMD ["/opt/wildfly-20.0.0.Final/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0","-bmanagement","0.0.0.0"]