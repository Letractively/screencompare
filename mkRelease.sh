#/bin/sh

pwd=`pwd`
tmp=/tmp/screencompare
rm -rf $tmp

sh mkJavadoc.sh > /dev/null

rm -rf $tmp
mkdir -p $tmp/data/reports
mkdir -p $tmp/data/screens
mkdir -p $tmp/examples
mkdir -p $tmp/lib
mkdir -p $tmp/lib/selenium-1
mkdir -p $tmp/lib/selenium-2
mkdir -p $tmp/lib/totop

cp src/examples/scripts/* $tmp/
cp src/examples/*.java $tmp/examples/
cp lib/*.jar $tmp/lib/
cp lib/selenium-1/*.jar $tmp/lib/selenium-1/
cp lib/selenium-2/*.jar $tmp/lib/selenium-2/
cp lib/totop/totop.exe $tmp/lib/totop/

sh mkBuild.sh
cd bin
jar -cf $tmp/lib/screencompare.jar screencompare
cd ..

cd $tmp/..
zip -r "$pwd/screencompare.zip" screencompare
rm -rf $tmp
