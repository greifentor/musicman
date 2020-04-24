# java -Dmusicman.password=krang -Dserver.port=$1 -jar gui/target/musicman-gui-1.0.jar
java -Dmusicman.password=krang -Dserver.port=$1 -Dspring.datasource.url=jdbc:hsqldb:file:~/db/musicman/musicman-db -jar gui/target/musicman-gui-1.0.jar
