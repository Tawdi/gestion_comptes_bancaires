 #!/bin/bash


javac -d out $(find src -name "*.java")

# generate
jar cfm bank.jar MANIFEST.MF -C out .

# Lancer le jar
java -jar bank.jar