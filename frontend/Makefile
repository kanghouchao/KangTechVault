build:
	docker buildx build --tag kang-tech-vault-web:0.0.1 .

run:
	docker run --name web -d -p 80:80 kang-tech-vault-web:0.0.1

shutdown:
	docker stop web || true
	docker rm web || true

restart: shutdown build run