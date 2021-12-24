Executing the program on command line without pom.xml with external dependencies

Compile
All jars and program are in this pwd
javac -g -d .  -cp curator-client-2.12.0.jar:curator-framework-2.12.0.jar:curator-recipes-2.12.0.jar LeaderElectionDemo.java


Execute
java -cp curator-client-2.12.0.jar:curator-framework-2.12.0.jar:curator-recipes-2.12.0.jar:. LeaderElectionDemo
