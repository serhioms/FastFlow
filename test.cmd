@echo off
echo main start
java -version
java -cp bin/exprint.jar org.junit.runner.JUnitCore test.ExprintTest
