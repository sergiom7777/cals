mvn clean compile test-compile;
mvn -Djacoco.destFile=target/coverage/jacoco.exec org.jacoco:jacoco-maven-plugin:prepare-agent install -Pcoverage-per-test;
mvn -X -Djacoco.dataFile=target/coverage/jacoco.exec -Djacoco.outputDirectory=./jacoco_ut org.jacoco:jacoco-maven-plugin:report
