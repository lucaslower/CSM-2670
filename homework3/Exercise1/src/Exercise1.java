/*
CSM 2670 -- Homework 3
Lucas Lower
02/07/2019
Exercise1.java
Displays a random fortune if no fortune number is supplied--otherwise displays the fortune number supplied.
(displays inside an ascii character's thought bubble akin to cowsay).
Usage: Exercise1 [debug] [fortune number]
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

        // init fortune num for try block
        int fortune_num = 0;

        // get the first int out of the array (if there is one)
        for(String arg : args){
            try{
                fortune_num = Integer.valueOf(arg);
                // ignore 0 if present
                if(fortune_num != 0){
                    break;
                }
            }
            catch(NumberFormatException ex){
                // skip non-integers
                continue;
            }
        }

        // parse fortunes into arraylist
        ArrayList<ArrayList<String>> fortunes = parse_fortunes();
        int fortune_count = fortunes.size();

        // check for
        if(fortune_num != 0){
            if(fortune_num > fortune_count){
                print_error("The fortune number you supplied does not exist---your file only has " + fortune_count + " fortunes in it.");
                System.exit(1);
            }
            else if(fortune_num < 0){
                print_error("The fortune number you supplied is negative! If you want a negative fortune, you should look in a mirror.");
                System.exit(1);
            }
            print_fortune(fortune_num, fortunes);
        }
        else{
            fortune_num = print_fortune(fortunes);
        }

        // print information if debugging
        if(Arrays.asList(args).contains("debug")){
            System.out.println("Debug Info -----------------------");
            System.out.println("Fortune count: "+fortune_count);
            if(fortune_num != 0){
                System.out.println("Fortune number supplied: "+fortune_num);
            }
            else{
                System.out.println("No number chosen, using random: "+fortune_num);
            }
        }
    }

    // print fortune given a fortune number
    private static void print_fortune(int fortune_num, ArrayList<ArrayList<String>> fortunes){
        ArrayList<String> fortune = fortunes.get(fortune_num-1); // minus one because fortunes are 1-indexed for end-user
        print_message(fortune);
        print_character();
    }

    // print function (generate fortune number)
    private static int print_fortune(ArrayList<ArrayList<String>> fortunes){
        int num_fortunes = fortunes.size();
        int fortune_num = (new Random()).nextInt(num_fortunes);
        print_fortune(fortune_num, fortunes);
        return fortune_num;
    }

    private static ArrayList<ArrayList<String>> parse_fortunes() throws FileNotFoundException{
        // init file
        File f = new File(FILE_NAME);
        // init return list
        ArrayList<ArrayList<String>> fortunes = new ArrayList<>();
        // file exists
        if(f.exists()){
            // init file scanner and arraylist for fortune storage
            Scanner reader = new Scanner(f);
            ArrayList<String> fortune = new ArrayList<>();
            // loop through all lines
            while(reader.hasNext()){
                String line = reader.nextLine();
                if(line.equals("%")){
                    // push (copy of) fortune into fortunes, clear fortune
                    // p.s. this is where I learned the hard way about reference vs. value
                    fortunes.add(new ArrayList<>(fortune));
                    fortune.clear();
                }
                else{
                    // push line into fortune
                    fortune.add(line);
                }
            }
            return fortunes;
        }
        // file doesn't exist
        else{
            print_error("Cannot find the \"fortunes.txt\" file. Exiting!");
            System.exit(1);
            return null;
        }
    }

    private static String repeat_char(char ch, int length){
        String ret_str = "";
        for(int i = 0; i < length; i++){
            ret_str += ch;
        }
        return ret_str;
    }

    private static ArrayList<String> filter_tabs(ArrayList<String> lines){
        ArrayList<String> ret = new ArrayList<>();
        for(String line : lines){
            line = line.replaceAll("\t", "    ");
            ret.add(line);
        }
        return ret;
    }


    // PRINT MESSAGE
    private static void print_message(ArrayList<String> lines){
        // filter out tabs (if not our length calcs are screwed up)
        lines = filter_tabs(lines);
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
    private static void print_error(String error){
        ArrayList<String> error_msg = new ArrayList<>(1);
        error_msg.add("ERROR: "+error);
        print_message(error_msg);
        print_character();
    }
}