# Tubes Algeo01-21044

This is a Java project which we named JAR Calculator (taken from our team's name) to solve multiple problems that involves matrix operations. This program can help its user to solve Linear Equation System using different methods, determine the determinant and inverse of a matrix, do polynomial interpolation, bicubic interpolation, and multiple linear regression.

## File structure

Algeo01-21044/ <br>
┣ bin/ <br>
┃ ┣ Aplikasi/ <br>
┃ ┃ ┣ BicubicInterpolationApp.class <br>
┃ ┃ ┣ DeterminantApp.class <br>
┃ ┃ ┣ InterpolasiApp.class <br>
┃ ┃ ┣ InverseApp.class <br>
┃ ┃ ┣ RLBApp.class <br>
┃ ┃ ┗ SPLApp.class <br>
┃ ┣ Matrix/ <br>
┃ ┃ ┣ Determinant.class <br>
┃ ┃ ┣ Inverse.class <br>
┃ ┃ ┣ Matrix.class <br>
┃ ┃ ┗ SPL.class <br>
┃ ┣ Utils/ <br>
┃ ┃ ┗ Utils.class <br>
┃ ┗ Main.class <br> 
┣ lib/ <br>
┣ src/ <br>
┃ ┣ Aplikasi/ <br>
┃ ┃ ┣ BicubicInterpolationApp.java <br> 
┃ ┃ ┣ DeterminantApp.java <br> 
┃ ┃ ┣ InterpolasiApp.java <br> 
┃ ┃ ┣ InverseApp.java <br>
┃ ┃ ┣ RLBApp.java <br>
┃ ┃ ┗ SPLApp.java <br>
┃ ┣ Matrix/ <br>
┃ ┃ ┣ Determinant.java <br>
┃ ┃ ┣ Inverse.java <br>
┃ ┃ ┣ Matrix.java <br>
┃ ┃ ┗ SPL.java <br>
┃ ┣ Utils/ <br>
┃ ┃ ┗ Utils.java <br>
┃ ┗ Main.java <br>
┣ test/ <br>
┃ ┣ input/ <br>
┃ ┗ output/ <br>
┣ .gitignore <br>
┗ README.md <br>

## How to run

Clone this repo https://github.com/chaerla/Algeo01-21044.git

### Using .class

1. Go to folder bin `cd bin`
2. Open in terminal
3. Run Main.class `java Main`
4. File inputs can be put into the `./test` folder

### Using .jar

1. Download the .jar file that is located in `./lib`
2. Put the .jar file in a certain folder
3. In root of the folder that contains .jar file, create a new folder named `test` for file input
4. cd to the folder containing the .jar file
5. Use `java -jar <jar-file-name>` to run the program
