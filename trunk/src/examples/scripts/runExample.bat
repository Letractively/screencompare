@ECHO OFF

IF "%1"=="" (
	ECHO Please specify the name of an example's class as argument 
	GOTO:EOF
)

set CP=.;lib/iText-5.0.2.jar;lib/junit-3.8.1.jar;lib/junit-4.8.2.jar;lib/screencompare.jar;lib/selenium-1/selenium-java-client-driver-sources.jar;lib/selenium-1/selenium-java-client-driver.jar;lib/selenium-1/selenium-server-sources.jar;lib/selenium-1/selenium-server.jar;lib/selenium-2/commons-codec-1.4.jar;lib/selenium-2/commons-collections-3.2.1.jar;lib/selenium-2/commons-httpclient-3.1.jar;lib/selenium-2/commons-io-1.4.jar;lib/selenium-2/commons-lang-2.4.jar;lib/selenium-2/commons-logging-1.1.1.jar;lib/selenium-2/cssparser-0.9.5.jar;lib/selenium-2/google-collect-1.0.jar;lib/selenium-2/hamcrest-all-1.1.jar;lib/selenium-2/htmlunit-2.7.jar;lib/selenium-2/htmlunit-core-js-2.7.jar;lib/selenium-2/jmock-2.4.0.jar;lib/selenium-2/jmock-junit3-2.4.0.jar;lib/selenium-2/jna.jar;lib/selenium-2/json-20080701.jar;lib/selenium-2/junit-3.8.1.jar;lib/selenium-2/nekohtml-1.9.14.jar;lib/selenium-2/sac-1.3.jar;lib/selenium-2/selenium-java-2.0a4.jar;lib/selenium-2/selenium-java-src-2.0a4.jar;lib/selenium-2/serializer-2.7.1.jar;lib/selenium-2/xalan-2.7.1.jar;lib/selenium-2/xercesImpl-2.9.1.jar;lib/selenium-2/xml-apis-1.3.04.jar;examples


javac -cp %CP% examples/%1.java
java -cp %CP% org.junit.runner.JUnitCore %1
