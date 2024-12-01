FROM bitnami/wildfly:latest

COPY ./target/web3.war /opt/bitnami/wildfly/standalone/deployments/

EXPOSE 8080

RUN /opt/bitnami/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
