cd %~dp0%/src/main/java

javac com/mehalter/life/model/*.java
javac com/mehalter/life/ui/*.java
javac com/mehalter/life/persistence/*.java
javac com/mehalter/life/*.java
java -cp ./ com.mehalter.life.GameOfLifeRunner
