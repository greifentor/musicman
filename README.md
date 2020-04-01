# musicman
A music player which could be controled via a web interface.


## Requirements

* Java 11
* Maven 3.5.4


## Build

Just clone the project and run `mvn clean install` on  shell.


## Run

After successful building the software start the application by:

`java -jar gui/target/musicman-gui-1.0.jar`

This starts the application in a demo mode with a memory database and the password `ncc1701`.

To have vital application set another password via property `musicman.password` and an alternative database setting the property `spring.datasource.url`. The Application is approved for running with `HSQLDB`. PostgreSQL may work also. Other DBMS could lead to additional effort to e. g. change the Liquibase scripts and / or the strategy for primary key generation.


## Todos

* Any and none :)
