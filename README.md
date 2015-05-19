
## Build


```
gradle build
```


## Bluemix

```
cf push
```

```
cf logs APP
```


## Eclipse

```
gradle eclipse
```

## NOTICE

Based on Bluemix sample Personality-insights github project
https://github.com/watson-developer-cloud/personality-insights-java/

Uses Watson Developer Cloud Java Wrapper
https://github.com/watson-developer-cloud/java-wrapper
http://watson-developer-cloud.github.io/java-wrapper/

Contains code from https://github.com/douglascrockford/JSON-java
- needed to copy to have code that works with Java 1.6 (.jar files required >=1.7)
so I could deploy to Bluemix libery builpack that has older IBM JDK 1.7
