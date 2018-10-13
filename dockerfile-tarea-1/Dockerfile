FROM node:6.14-alpine
MAINTAINER alberto.castro
EXPOSE 3000
WORKDIR /usr/src/app
COPY . .
RUN apk add --update tini && npm install && npm cache clean --force
ENTRYPOINT [ "/sbin/tini", "--", "node", "./bin/www" ]