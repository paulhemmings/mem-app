# mem-app

Work in progress. This is an existing Facebook app I am migrating from  Google App Engine to AWS.

##### steps  

* Migrate from Eclipse to Gradle for dependency management - done
* Replace Google App Engine with Tomcat - done
- Replace Big Table with MongoDB
- Research deploying a java / tomcat / mongoDB app to EC2

##### research

* https://s3.amazonaws.com/quickstart-reference/mongodb/latest/doc/MongoDB_on_the_AWS_Cloud.pdf
- https://plugins.gradle.org/search?term=tomcat
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
