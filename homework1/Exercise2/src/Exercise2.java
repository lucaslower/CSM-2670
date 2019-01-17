/*
CSM 2670 Exercise Set 1

Lucas Lower
01/17/2018

File: Exercise2.java

This program prints a right triangle with an outline of asterisks (*),
with the height (in rows) being supplied by the first CLI argument.

NOTE: This code is essentially taken verbatim from Exercise1, with some minor changes
to the print_triangle() method. If they weren't separate exercises I would just make a
new method in the same class, e.g. print_triangle_outline().
*/

public class Exercise2 {

    // no magic numbers here
    private static final int MIN_ROW_COUNT = 1;
    private static final int MAX_ROW_COUNT = 80;

    // MAIN
    public static void main(String[] args) {
        // check if first arg exists
        if(args.length == 1){
            // parse int and check if valid
            int num_rows = Integer.valueOf(args[0]);
            if (num_rows > MIN_ROW_COUNT && num_rows < MAX_ROW_COUNT) {
                print_triangle(num_rows);
            }
            else{
                print_error("arg_value");
            }
        }
        else{
            print_error("arg_count");
        }
    }

    // PRINT TRIANGLE
    private static void print_triangle(int num_rows){
        // print single asterisk
        System.out.println("*");
        // loop middle rows (total count - 2)
        for(int i = 0; i < num_rows-2; i++) {
            // init line with leading asterisk
            String line = "*";
            // loop cols
            for(int col = 0; col < i; col++){
                line += " ";
            }
            // add trailing asterisk
            line += "*";
            // print the line
            System.out.println(line);
        }
        // print last row if count > 1
        if(num_rows > 1){
            for(int i = 0; i < num_rows; i++){
                System.out.print("*");
            }
        }
    }

    // PRINT ERROR
    private static void print_error(String type){
        switch(type){
            // argument count != 1
            case "arg_count":
                System.err.println("You have supplied more than one argument.");
                System.err.println("This program only takes one: row count. Try again.");
                break;
            // argument not within 1--80
            case "arg_value":
                System.err.println("The number of rows you have supplied is not valid.");
                System.err.println("Please supply a number between 1 and 80.");
                break;
        }
    }
}
