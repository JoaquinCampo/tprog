#!/bin/bash

cd tpgr21-main/Tarea2
mvn compile
mvn package

cd target
mv Tarea2-0.0.1-SNAPSHOT.war ../../../apache-tomcat-10.1.15/webapps/Tarea2.war

cd ../../Mobile

mvn compile
mvn package

cd target
mv Mobile-0.0.1-SNAPSHOT.war ../../../apache-tomcat-10.1.15/webapps/Mobile.war

cd ../../../apache-tomcat-10.1.15/bin
./startup.sh

cd ../../tpgr21-main/ServidorCentral
mvn compile
mvn package

cd target
java -jar ServidorCentral-0.0.1-SNAPSHOT-jar-with-dependencies.jar
