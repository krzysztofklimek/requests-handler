#!/bin/bash
rm -rf target/
mvn install package
chmod +x run.sh