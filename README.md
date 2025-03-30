# Microsservice-compelto
Descrever o que um microsservice precisa e o que não precisa

## Para executar o projeto 

Suba o mysql e o rabbitmq para poder rodar o projeto

```bash 
docker-compose up -d
```

Ficou como atividade criar um docker para os microsserviços então vou deixar pra outro dia 

## Para executar tudo você pode simplesmente dar o comando 

```bash
./start.sh
```

Isso irá fazer subir todos os microsserviços e o Service discorvery e Gateway

## Para vistar o gateway 

```bash
http://localhost:8082
```

## Para acessar o service discovery

```bash
http://localhost:8081
```

## Endpoints para os microsserviços

### Microsserviço de Usuário

  1. POST http://localhost:8082/microsservice/pedidos
     Content-Type: application/json
   ```json
        {
        "cpf": "072.168.777-33",
        "itens": [
        {
        "quantidade": 2,
        "descricao": "laranja",
        "valorUnitario": 8.89
        },{
        "quantidade": 3,
        "descricao": "macarrão",
        "valorUnitario": 38.40
        }
        ]
        }
   ```
  2. 