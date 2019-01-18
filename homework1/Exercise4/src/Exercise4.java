/*
CSM 2670 Exercise Set 1

Lucas Lower
01/17/2018

File: Exercise4.java

This program prints a stick figure walking up the stairs, as described in Reges Chapter 2 Project 4.
*/

public class Exercise4 {

    // no magic numbers here
    private static final int MIN_STAIR_COUNT = 0;
    private static final int MAX_STAIR_COUNT = 20;

    // MAIN
    public static void main(String[] args) {
        // check if first arg exists
        if(args.length == 1){
            // parse int and check if valid
            int num_stairs = Integer.valueOf(args[0]);
            if (num_stairs >= MIN_STAIR_COUNT && num_stairs <= MAX_STAIR_COUNT) {
                print_stairs(num_stairs);
            }
            else{
                print_error("arg_value");
            }
        }
        else{
            print_error("arg_count");
        }
    }

    public static String build_offset(int offset){
        String ret_str = "";
        for(int i = 0; i < offset; i++){
            ret_str += " ";
        }
        return ret_str;
    }

    // PRINT STAIRS
    private static void print_stairs(int num_stairs){
        // set total width
        int total_width = ((num_stairs+1)*5)+1;
        // loop stairs
        for(int step = 0; step < num_stairs; step++){
            int offset = (5*(num_stairs - (step+1)));
            // loop rows in step
            for(int row = 0; row < 3; row++){
                String stair_out = build_offset(offset);
                switch(row){
                    case 0:
                        stair_out += "  o  ******";
                        break;
                    case 1:
                        stair_out += " /|\\ *";
                        break;
                    case 2:
                        stair_out += " / \\ *";
                        break;
                    default:
                        stair_out += "";
                        break;
                }
                int whitespace = total_width - stair_out.length() - 1;
                stair_out += build_offset(whitespace);
                if(row > 0 || step > 0){
                    stair_out += "*";
                }
                System.out.println(stair_out);
            }
        }
        if(num_stairs > 0) {
            for (int i = 0; i < total_width; i++) {
                System.out.print("*");
            }
        }
        System.out.println();
    }

    // PRINT ERROR
    private static void print_error(String type){
        switch(type){
            // argument count != 1
            case "arg_count":
                System.err.println("You have supplied the wrong number of arguments.");
                System.err.println("This program takes only one: stair count. Try again.");
                break;
            // argument not within 0--20
            case "arg_value":
                System.err.println("The number of stairs you have supplied is not valid.");
                System.err.println("Please supply a number between 0 and 20.");
                break;
        }
    }
}
