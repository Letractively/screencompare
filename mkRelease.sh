#/bin/sh

dir=../release

sh mkJavadoc.sh > /dev/null

rm -rf $dir
mkdir -p $dir/data/reports
mkdir -p $dir/data/screens
mkdir -p $dir/examples
mkdir -p $dir/lib
mkdir -p $dir/lib/selenium-1
mkdir -p $dir/lib/selenium-2
mkdir -p $dir/lib/totop

cp src/examples/scripts/* $dir/
cp src/examples/*.java $dir/examples/
cp lib/*.jar $dir/lib/
cp lib/selenium-1/*.jar $dir/lib/selenium-1/
cp lib/selenium-2/*.jar $dir/lib/selenium-2/
cp lib/totop/totop.exe $dir/lib/totop/

sh mkBuild.sh
cd bin
jar -cf ../$dir/lib/screencompare.jar screencompare
