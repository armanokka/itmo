FROM golang:1.19.1
FROM ubuntu:latest

WORKDIR app
COPY ./.env ./
COPY ./app ./
EXPOSE $PORT
ENTRYPOINT ["./app"]