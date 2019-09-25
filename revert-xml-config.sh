#!/usr/bin/env bash

PRG_DIR=$(cd $(dirname "$0"); pwd)

cd ${PRG_DIR}

rm -rf *-cassandra-dao
git status | grep -i modified | grep \.xml$ | awk '{ print $2 }' | xargs -I {} git checkout {}

