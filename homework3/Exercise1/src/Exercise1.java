/*
CSM 2670 Exercise Set 1

Lucas Lower
01/17/2018

File: Exercise6.java

This program prints a given message, with the message appearing in a thought bubble,
similar to cowsay.
*/

import java.util.*;
import java.io.*;

public class Exercise1 {

    // thought bubble chars
    private static final char LINE_CHAR = '-';
    private static final char LEFT_CHAR = '(';
    private static final char RIGHT_CHAR = ')';
    // fortune file name
    private static final String FILE_NAME = "fortunes.txt";


    // MAIN
    public static void main(String[] args) throws FileNotFoundException {

        // init args
        boolean debug = false;
        boolean num_set = false;
        int fortune_num = 0;

        // check for debug
        if(Arrays.asList(args).contains("debug")){
            debug = true;
        }

        // get the first int out of the array (if there is one)
        for(String arg : args){
            try{
                fortune_num = Integer.valueOf(arg);
                num_set = true;
                break;
            }
            catch(NumberFormatException ex){
                continue;
            }
        }

        // get fortune count
        int num_fortunes = fortune_count(FILE_NAME);
        if(num_fortunes == -1){
            System.exit(-1);
        }

        // print information if debugging
        if(debug == true){
            System.out.println("Debug Information:");
            System.out.println("Fortune count: "+num_fortunes);
            if(num_set == true){
                System.out.println("Fortune number supplied: "+fortune_num);
            }
            else{
                System.out.println("No number chosen, using random: ");
            }
        }
    }

    private static int fortune_count(String file_name) throws FileNotFoundException{
        // determine fortune count
        File f = new File(file_name);
        int num_fortunes = -1;
        // file exists
        if(f.exists()){
            num_fortunes = 0;
            Scanner f_scan = new Scanner(f);
            String line = "";
            while(f_scan.hasNextLine()){
                line = f_scan.nextLine();
                if(line == "%"){
                    num_fortunes++;
                }
            }
            return num_fortunes;
        }
        // file doesn't exist
        else{
            System.err.println("Cannot find the \"fortunes.txt\" file. Exiting . . ");
            return num_fortunes;
        }
    }

    private static String repeat_char(char ch, int length){
        String ret_str = "";
        for(int i = 0; i < length; i++){
            ret_str += ch;
        }
        return ret_str;
    }


    // PRINT MESSAGE
    private static void print_message(String[] lines){
        // determine maximum length
        int max_length = 0;
        for(String line : lines){
            if(line.length() > max_length){
                max_length = line.length();
            }
        }
        // print top line
        System.out.print(' ');
        System.out.print(repeat_char(LINE_CHAR, max_length+4));
        System.out.println(' ');

        // loop through lines and print each one
        for(String line : lines){
            // print left char
            System.out.print(LEFT_CHAR);
            // determine length diff
            int length_diff = max_length - line.length();
            System.out.print("  " + line + repeat_char(' ', length_diff+2));
            // print right char
            System.out.println(RIGHT_CHAR);
        }
        // print bottom line
        System.out.print(' ');
        System.out.print(repeat_char(LINE_CHAR, max_length+4));
        System.out.println(' ');
    }

    // PRINT CHARACTER
    private static void print_character(){
        String char_out = "";
        char_out += "    o       ___\n";
        char_out += "     o     /---\\\n";
        char_out += "       o   |* *|\n";
        char_out += "           { V }\n";
        char_out += "        ____`~`____\n";
        char_out += "        | |  .  | |\n";
        char_out += "        | |  .  | |\n";
        char_out += "        | |  .  | |\n";
        char_out += "        ( )-----( )\n";
        char_out += "          |  |  |\n";
        char_out += "          |  |  |\n";
        char_out += "          |  |  |\n";
        char_out += "          |  |  |\n";
        char_out += "        ([[[| |]]])\n";
        System.out.print(char_out);
        System.out.println();
    }


    // PRINT ERROR
    private static void print_error(){
        System.err.println("You haven't supplied a message. Try again.");
    }
}