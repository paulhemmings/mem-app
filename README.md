# mem-app

#### Install Tomcat locally
http://tomcat.apache.org/download-70.cgi

download the zip
copy to Tools folder
extract the zip
create a symlink between the location and Library so we can swap and change later on

''''
$ sudo ln -s ~/Tools/apache-tomcat-7.0.59 /Library/Tomcat
$ sudo chown -R [user] /Library/Tomcat
$ sudo chmod +x /Library/Tomcat/bin/*.sh
$ /Library/Tomcat/bin/startup.sh
$  /Library/Tomcat/bin/shutdown.sh
''''
