#!/bin/bash

export JAVA_HOME="/home/higorlucas/.asdf/installs/java/adoptopenjdk-21.0.6+7.0.LTS"

export PATH=$JAVA_HOME/bin:$PATH

M2_COMMAND = "-Dmaven.repo.local=$HOME/.m2Computador/repository"
# Inicia Eureka
echo "Iniciando Eureka Service..."
cd eurekaService && mvn spring-boot:run $M2_COMMAND &
sleep 10

# Inicia Microservice
echo "Iniciando Microsservice..."
cd ../microsservice && mvn spring-boot:run $M2_COMMAND &
sleep 5

# Inicia MS Pagamentos
echo "Iniciando MS Pagamentos..."
cd ../ms-pagamentos && mvn spring-boot:run $M2_COMMAND &
sleep 5

# Inicia MS Usuários
echo "Iniciando MS Usuários..."
cd ../ms-usuarios && mvn spring-boot:run $M2_COMMAND &
sleep 5

# Inicia API Gateway
echo "Iniciando API Gateway..."
cd ../api-gateway && mvn spring-boot:run $M2_COMMAND &
sleep 5

echo "Todos os serviços foram iniciados!"
