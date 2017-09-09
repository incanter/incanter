FROM clojure

RUN mkdir -p /usr/src/incanter

WORKDIR /usr/src/incanter

COPY . /usr/src/incanter/

RUN apt-get update && apt-get -y install libgfortran3 && rm -rf /var/lib/apt/lists/*

RUN lein modules install

CMD lein modules test