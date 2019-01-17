/*
CSM 2670 Exercise Set 1

Lucas Lower
01/17/2018

File: Exercise3.java

This program prints a right triangle with an outline of asterisks (*),
with the height (in rows) being supplied by the first CLI argument.

I added the ability to use a different fill char than a space, and a different edge char than an asterisk.
My favorite combo is EDGE_CHAR = '^' and FILL_CHAR = '-'.

NOTE: As with Exercise2, this code is essentially taken verbatim from Exercise1, with some minor changes
to the print_triangle() method. If they weren't separate exercises I would just make aEDGE_SYMBOL
new method in the same class, e.g. print_triangle_outline_centered().
*/

public class Exercise3 {

    // no magic numbers here
    private static final int MIN_ROW_COUNT = 1;
    private static final int MAX_ROW_COUNT = 80;
    private static final char EDGE_CHAR = '^';
    private static final char FILL_CHAR = '-';

    // MAIN
    public static void main(String[] args) {
        // check if first arg exists
        if(args.length == 1){
            // parse int and check if valid
            int num_rows = Integer.valueOf(args[0]);
            if (num_rows >= MIN_ROW_COUNT && num_rows <= MAX_ROW_COUNT) {
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
        // loop top rows (everything but the last)
        for(int row = 0; row < num_rows-1; row++) {
            // print leading spaces
            for (int i = num_rows; i > row+1; i--) {
                System.out.print(' ');
            }
            // print first asterisk
            System.out.print(EDGE_CHAR);
            // row 0 does not have 2 asterisks
            if(row > 0){
                // print intermediate zeroes
                for(int i = 0; i < (2*row)-1; i++){
                    System.out.print(FILL_CHAR);
                }
                // print second asterisk
                System.out.print(EDGE_CHAR);
            }
            // print dividing line
            System.out.println();
        }
        // print final line (or the only asterisk for num_rows == 1)
        for(int col = 0; col < (2*num_rows)-1; col++){
            System.out.print(EDGE_CHAR);
        }
        // print final newline
        System.out.println();
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
