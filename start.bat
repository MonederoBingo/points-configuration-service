echo "::::::::::STARTING POINTS CONFIGURATION SERVICE:::::::::::"
cd %~dp0
call gradlew -x test
call java -jar build/libs/points_configuration-0.0.1-SNAPSHOT.jar
