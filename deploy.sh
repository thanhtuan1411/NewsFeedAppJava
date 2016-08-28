mvn clean install
sh /Users/tdo/workspaces/politico/tomcat/bin/shutdown.sh
cp ./target/NewsFeedApp.war /Users/tdo/workspaces/politico/tomcat/webapps/
sh /Users/tdo/workspaces/politico/tomcat/bin/startup.sh
