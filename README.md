# Technical Schibsted Test

Technical test with a login form and 3 private pages.

##Requirements

You will need to have installed in your machine:
•JDK 8 (Standard Edition)
•RxJava
•Twig or Mustache for HTML templating
•Jackson or Gson
•JUnit 4
•AssertJ
•Sqlite or H2

# Build application
gradlew build


## Create runnable jar
gradlew jar


## Run application
java -jar build/libs/schibsted-app-1.0-SNAPSHOT.jar


## Available URIs by means of Web Browser

```
http://localhost:8080/app/login/login.html
http://localhost:8080/app/pages/page1.html
http://localhost:8080/app/pages/page2.html
http://localhost:8080/app/pages/page3.html
http://localhost:8080/app/login/logout.html
```
## Available users and passwords

Usernames | Password | ROL | May access
--------- | -------- | --- | ----------
jmcarne | 123456 | ADMIN | Everything and POST API REST
USER1 | 123456 | ROLE_1 | http://localhost:8080/app/pages/page1.html
USER2 | root | ROLE_2 | http://localhost:8080/app/pages/page2.html


## Available roles

ROLE | Description
---- | -----------
ROLE1 | App page 1 access
ROLE2 | App page 2 access
ROLE3 | App page 3 access
ADMIN | App Admin access