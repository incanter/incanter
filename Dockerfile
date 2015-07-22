FROM clojure

RUN mkdir -p /usr/src/incanter

WORKDIR /usr/src/incanter

COPY . /usr/src/incanter/

RUN lein modules install

CMD lein modules test