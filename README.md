# prosper-1099-parser
This is a simple project to convert PDF files containing Prosper 1099-B transactions to CSV format.

### Prerequisites
- [JDK 1.8] is installed
- An internet connection is required to download gradle dependencies

### Steps to run:
1. Download/clone the project. 
1. Open the command prompt and `cd` to the root directory of the project.
1. Run the command `./gradlew clientInstall bootRun` to download client dependencies and start the service (on Windows use `gradlew.bat` instead of `./gradlew`).
1. Browse to: http://localhost:8080

### Tech Used
* [Spring Boot]
* [Gradle]
* [Apache PDFBox]
* [Apache Commons CSV]
* [Bower]
* [Spring Boot Asset Pipeline Gradle Adapter]
* [Client Dependencies Gradle Plugin]
* [JQuery]
* [Materialize]
* [Bootstrap]
* [SLF4J]

[JDK 1.8]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[Spring Boot]: http://projects.spring.io/spring-boot/
[Gradle]: http://gradle.org/
[Apache PDFBox]: https://pdfbox.apache.org/
[Apache Commons CSV]: https://commons.apache.org/proper/commons-csv/
[Bower]: https://github.com/bower/bower
[Spring Boot Asset Pipeline Gradle Adapter]: https://github.com/bertramdev/asset-pipeline/tree/master/asset-pipeline-spring-boot    
[Client Dependencies Gradle Plugin]: https://github.com/craigburke/client-dependencies-gradle
[JQuery]: https://github.com/jquery/jquery
[Materialize]: https://github.com/Dogfalo/materialize
[Bootstrap]: https://github.com/twbs/bootstrap
[SLF4J]: https://github.com/qos-ch/slf4j
