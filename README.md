# NewsFeedAppJava
Technology stask: Spring MVC with Augular JS
Database : simple xml file (newsfeeddb.xml) to storage the newfeeds. 
Webserver needed : Tomcat server
To run to project : mvn clean install
go to target folder and copy NewsFeedApp.war to Tomcat webapps folder
Open http://localhost:8080/NewsFeedApp/ to view the application.
Main files :
NewsFeed.java : NewsFeed model
NewsFeedManagement.jsp main view file
NewsFeedServiceImpl.java Java business logic
NewsFeedRestController.java : Main Spring MVC Rest Controller
newsfeed_controller.js : Angular Js main controller js file
newsfeed_serive.js : REST service js file
