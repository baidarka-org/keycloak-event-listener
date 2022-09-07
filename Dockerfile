FROM quay.io/keycloak/keycloak:19.0.0

ARG APPLICATION_JAR=target/*.jar

COPY ${APPLICATION_JAR} /opt/keycloak/providers

RUN /opt/keycloak/bin/kc.sh build --metrics-enabled=true
