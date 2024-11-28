init: build run
init-d: build run-d

update: pull build restart

pull:
	git pull

restart:
	docker-compose restart


run:
	docker-compose up --remove-orphans
run-d:
	docker-compose up --remove-orphans -d

build:
	GOOS=linux GOARCH=amd64 go build -o app ./cmd/api
	docker-compose build