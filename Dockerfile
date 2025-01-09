FROM bitnami/wildfly:latest

COPY ./target/web3.war /opt/bitnami/wildfly/standalone/deployments/web3.war
COPY postgres-ds.xml /opt/bitnami/wildfly/standalone/deployments/
COPY driver/postgresql-42.7.4.jar /opt/bitnami/wildfly/standalone/deployments/

RUN /opt/bitnami/wildfly/bin/add-user.sh admin admin --silent

EXPOSE 8080

CMD /opt/bitnami/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
