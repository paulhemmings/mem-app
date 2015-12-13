# mem-app

Work in progress. This is an old Facebook app I wrote a few years back, that I have decided to migrate from  Google App Engine to AWS.

##### steps  

* Migrate from Eclipse to Gradle for dependency management - done
* Replace Google App Engine with Tomcat - done
- Replace Big Table with MongoDB - done
- Research deploying a java / tomcat / mongoDB app to AWS

##### research

* https://s3.amazonaws.com/quickstart-reference/mongodb/latest/doc/MongoDB_on_the_AWS_Cloud.pdf
- https://plugins.gradle.org/plugin/com.boxfuse.client
- https://boxfuse.com/

#### Install, build, run.

````
$ git clone https://github.com/paulhemmings/mem-app.git
$ cd mem-app/
$ ls
$ gradle wrapper
$ ./gradlew clean build
$ ./gradlew start
````
