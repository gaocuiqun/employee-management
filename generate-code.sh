#!/usr/bin/env bash

PRG_DIR=$(cd $(dirname "$0"); pwd)

cd ${PRG_DIR}
cd ..

java -Dsymbol.naming=unix_c -Doutput.dir=$(pwd) -jar ~/github/apuex/spring-boot-solution/codegen/target/scala-2.12/codegen.jar generate-all employee-management/model.xml


