/*
CSM 2670 Exercise Set 1

Lucas Lower
01/17/2018

File: Exercise5.java

This program prints a christmas tree as described in Reges Chapter 3 Project 1.
*/

public class Exercise5 {

    // no magic numbers here
    private static final int MIN_SEG_COUNT = 1;
    private static final int MAX_SEG_COUNT = 10;
    private static final int MIN_SEG_HEIGHT = 1;
    private static final int MAX_SEG_HEIGHT = 10;
    private static final int BASE_LENGTH = 7;
    private static final char PRINT_CHAR = '*';

    // MAIN
    public static void main(String[] args) {
        // check if first arg exists
        if(args.length == 2){
            // parse first arg and check if valid
            int num_segs = Integer.valueOf(args[0]);
            boolean num_segs_good = num_segs >= MIN_SEG_COUNT && num_segs <= MAX_SEG_COUNT;
            // parse second arg and check if valid
            int seg_height = Integer.valueOf(args[1]);
            boolean seg_height_good = seg_height >= MIN_SEG_HEIGHT && seg_height <= MAX_SEG_HEIGHT;
            // test
            if(num_segs_good && seg_height_good){
                print_tree(num_segs, seg_height);
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

    // PRINT TREE
    private static void print_tree(int num_segs, int seg_height){
        // calc total width
        int total_width = 1+(2*(seg_height-1))+(2*(num_segs-1));
        // check that total width is at least equal to BASE_LENGTH
        if(total_width < BASE_LENGTH){
            total_width = BASE_LENGTH;
        }
        // loop segments
        for(int seg = 0; seg < num_segs; seg++){
            // calc first line offset
            int first_offset = ((total_width-1)/2)-seg;
            // loop rows in segment
            for(int row = seg; row < seg_height+seg; row++){
                // print offset for line
                System.out.print(build_offset(first_offset - (row-seg)));
                // loop chars in row
                for(int ch = 0; ch < (2*row)+1; ch++){
                    System.out.print(PRINT_CHAR);
                }
                // newline
                System.out.println();
            }
        }
        // print base shaft
        int half_offset = (total_width-1)/2;
        for(int i = 0; i < 2; i++){
            System.out.print(build_offset(half_offset));
            System.out.print(PRINT_CHAR);
            System.out.println();
        }
        // print base
        System.out.print(build_offset(half_offset - (BASE_LENGTH-1)/2));
        for(int i = 0; i < BASE_LENGTH; i++){
            System.out.print(PRINT_CHAR);
        }
        // print final newline
        System.out.println();
    }


    // PRINT ERROR
    private static void print_error(String type){
        switch(type){
            // argument count != 1
            case "arg_count":
                System.err.println("You have supplied the wrong number of arguments.");
                System.err.println("This program takes two: number of segments, and segment height. Try again.");
                break;
            // argument not within 0--20
            case "arg_value":
                System.err.println("The arguments you supplied are not valid.");
                System.err.println("Please supply two numbers between 1 and 10.");
                break;
        }
    }
}
