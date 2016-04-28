FROM ubuntu:14.04

RUN \
  apt-get install -y --no-install-recommends software-properties-common \
  && add-apt-repository ppa:webupd8team/java \
  && apt-get update \
  && echo debconf shared/accepted-oracle-license-v1-1 select true |  debconf-set-selections \
  && echo debconf shared/accepted-oracle-license-v1-1 seen true |  debconf-set-selections \
  && apt-get install -y --no-install-recommends oracle-java8-installer

EXPOSE 8080
EXPOSE 8081

VOLUME /var/log/payments-service

ADD config/config.yml config.yml
ADD target/dropwizard-marathon-stub*.jar service.jar

CMD sh -c "java -jar -XX:+${GC_ALGO-UseG1GC} -Xms${JAVA_PROCESS_MIN_HEAP-256m} -Xmx${JAVA_PROCESS_MAX_HEAP-256m} service.jar server config.yml"
