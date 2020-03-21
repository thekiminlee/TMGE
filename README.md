# TMGE

Tile Matching Game Environment game engine.

### Dependencies

- ##### JDK 11

[AdoptOpenJDK 11](https://adoptopenjdk.net/?variant=openjdk11)

```JAVA_HOME=<path/to/java>/jdk-11.0.6.10-hotspot/```

##### JavaFX 11.0.12

[JavaFX 11](https://gluonhq.com/products/javafx/)

```PATH_TO_FX=<path/to/javafx>/javafx-sdk-11.0.2/lib```

##### Maven

[Maven, manual install](https://maven.apache.org/install.html)

or through your package manager

```choco install maven```

```sudo apt install maven``` 


### Verify that it works on your setup

##### Compile and create the jar	

```mvn clean package```

##### Run the jar from the project root directory

```java --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.fxml -jar .\target\TMGEngine-1.0-SNAPSHOT.jar```
