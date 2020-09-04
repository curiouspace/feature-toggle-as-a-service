#!/usr/bin/env bash

echo "===================================================="
echo Maven Build Project
echo "===================================================="
mvn clean install

echo "===================================================="
echo Docker Build ft-server
echo "===================================================="
cd ft-server
docker build -t dcodealpha/ft-server:latest .

echo "===================================================="
echo Docker Build ft-sample-client
echo "===================================================="
cd ../ft-samples/ft-sample-client
docker build -t dcodealpha/ft-sample-client:latest .

echo "===================================================="
echo Docker Build ft-sample-client-02
echo "===================================================="
cd ../ft-sample-client-02
docker build -t dcodealpha/ft-sample-client-02:latest .