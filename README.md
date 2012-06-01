
Instructions
============

Build:
````
mvn clean package
```

Deploy resulting war `target/dispatch-test.war`.

JSP rendering via `AsyncContext.dispatch`:
* ServletA [http://localhost:8080/dispatch-test/servletA](http://localhost:8080/dispatch-test/servletA)
* ServletB [http://localhost:8080/dispatch-test/servletB](http://localhost:8080/dispatch-test/servletB)

JSP rendering via `request.getRequestDispatcher`:
* ServletA [http://localhost:8080/dispatch-test/servletA?forward=true](http://localhost:8080/dispatch-test/servletA?forward=true)
* ServletB [http://localhost:8080/dispatch-test/servletB?forward=true](http://localhost:8080/dispatch-test/servletB?forward=true)

ServletA forwards to ServletB, which starts async processing and attempts to render a JSP. 
Accessing either ServletA or ServletB should produce the same result.

Eclipse
=======

Generate settings:
````
mvn -U -Dwtpversion=2.0 eclipse:clean eclipse:eclipse -DdownloadSources=true
````

Import project into Eclipse.

 