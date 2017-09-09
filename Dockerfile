FROM clojure

RUN mkdir -p /usr/src/incanter

WORKDIR /usr/src/incanter

COPY . /usr/src/incanter/


RUN apt-get update
RUN apt-get -y install libgfortran3 

RUN lein modules install

CMD lein modules test