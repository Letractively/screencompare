#/bin/sh

cp=$(echo lib/*.jar lib/selenium-1/*.jar lib/selenium-2/*.jar | sed -e 's# #:#g')

javac -d bin -classpath $cp -sourcepath src src/screencompare/ScreencompareTestCase.java src/screencompare/driver/DriverSelenium1.java src/screencompare/driver/DriverSelenium2.java

cd bin
jar -cf screencompare.jar screencompare
