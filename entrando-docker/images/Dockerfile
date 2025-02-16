FROM quay.io/keycloak/keycloak:26.0.2

ENV KC_DB=postgres
ENV KC_FEATURES=token-exchange,admin-fine-grained-authz
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true
ENV KC_HOSTNAME=auth.iaticket.net
ENV KC_PROXY=edge
ENV KC_HTTP_ENABLED=true

RUN /opt/keycloak/bin/kc.sh build