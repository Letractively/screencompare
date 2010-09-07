#!/bin/sh

cp=$(echo . lib/*.jar lib/selenium-1/*.jar lib/selenium-2/*.jar examples | sed -e 's# #:#g')

if [ ! -f examples/$1.java ]; then
	echo "Please specify the name of an example's class as argument"
	echo "Try these:" $(echo examples/*.java | sed -e 's#examples\/\([^ ]*\)\.java#\1#g;s# #, #')
	exit 1
fi

javac -cp $cp examples/$1.java
java -cp $cp org.junit.runner.JUnitCore $1
