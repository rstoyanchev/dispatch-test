
Instructions
============

Build:
````
mvn clean package
```

Deploy resulting war `target/dispatch-test.war`.

Access ServletA [http://localhost:8080/dispatch-test/servletA](http://localhost:8080/dispatch-test/servletA)
and ServletB [http://localhost:8080/dispatch-test/servletB](http://localhost:8080/dispatch-test/servletB).

ServletA forwards to ServletB, which starts async processing and attempts to render a JSP. 
Accessing either ServletA or ServletB should produce the same result.

In `ServletB.java` try switching between using `asyncContext.dispatch("")`
and `RequestDispatcher.forward` from the async thread.

Eclipse
=======

Generate settings:
````
mvn -U -Dwtpversion=2.0 eclipse:clean eclipse:eclipse -DdownloadSources=true
````

Import project into Eclipse.

 