## Helpful Links


```
Go to andrew-stopera-ibm-internship.blogspot.com to find full directions on how to use this source code to create your very own web portal. This project was done by a high school students over 2 weeks at IBM for an internship project. We hope that those who are high school students with an interest in coding will be able to fulfill the project following the steps in the blog above. 
```


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
