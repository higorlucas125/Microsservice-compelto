#!/bin/bash

export JAVA_HOME="/home/higorlucas/.asdf/installs/java/adoptopenjdk-21.0.6+7.0.LTS"
export PATH=$JAVA_HOME/bin:$PATH

M2_COMMAND="-Dmaven.repo.local=$HOME/.m2Computador/repository"

# Inicia API Gateway com encoding UTF-8
echo "Iniciando API Gateway..."
cd ../api-gateway && mvn dependency:resolve $M2_COMMAND && mvn clean install -X $M2_COMMAND -Dfile.encoding=UTF-8