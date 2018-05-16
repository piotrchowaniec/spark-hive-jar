# About
Example application to show problem with datanucleus plugin discovery mechanism when application is build as single executable jar.

Probably there are some not important and not needed details included in this application, but this was build from existing project to demonstrate problem, not to be a minimal running example.

# Build
To build application execute `./gradlew assemble`

To run application execute `java -Dloader.main=com.example.Application -jar build/libs/spark-hive-jar-1.0-SNAPSHOT.jar` from main project dir.

To run with open debug port execute `java -Dloader.main=com.example.Application -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar build/libs/spark-hive-jar-1.0-SNAPSHOT.jar`.

# Usage
By default application starts on 8080 port. One endpoint `run` is available. When called `GET` method on `http://localhost:8080/run` new database is created by spark and `show tables` command is executed on this new database. See `com.example.controller.RunController` for details.