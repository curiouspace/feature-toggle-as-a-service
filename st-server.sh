#!/usr/bin/env bash
echo "===================================================="
echo Compling feature-toggle
echo "===================================================="
mvn clean install -P dev
echo "===================================================="
echo CD into ft-service
echo "===================================================="
cd ./ft-server
echo "===================================================="
echo Starting feature-toggle Server
echo "===================================================="
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDB_PASSWORD=admin -DDB_USERNAME=admin -DDB_URL=jdbc:postgresql://localhost:5432/boot"
