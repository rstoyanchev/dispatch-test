
Instructions
============

Build:
````
mvn clean package
```

Deploy resulting war `target/dispatch-test.war`.

Go to http://localhost:8080/dispatch-test/

Eclipse
=======

Generate settings:
````
mvn -U -Dwtpversion=2.0 eclipse:clean eclipse:eclipse -DdownloadSources=true
````

Import project into Eclipse

 
