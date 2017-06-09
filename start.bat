echo "::::::::::STARTING POINTS CONFIGURATION SERVICE:::::::::::"
cd %~dp0
call mvn clean install -DskipTests
call java -jar target/points_configuration-0.0.1-SNAPSHOT.jar
