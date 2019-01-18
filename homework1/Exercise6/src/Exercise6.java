/*
CSM 2670 Exercise Set 1

Lucas Lower
01/17/2018

File: Exercise6.java

This program prints a given message, with the message appearing in a thought bubble,
similar to cowsay.
*/

public class Exercise6 {

    private static final char LINE_CHAR = '-';
    private static final char LEFT_CHAR = '(';
    private static final char RIGHT_CHAR = ')';

    // MAIN
    public static void main(String[] args) {
        // check if at least one arg exists
        if(args.length > 0){
            // join args to one string
            String message = String.join(" ", args);
            // print message
            print_message(message);
            // print character
            print_character();
        }
        else{
            print_error();
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
    private static void print_message(String message){
        // print top line
        System.out.print(' ');
        System.out.print(repeat_char(LINE_CHAR, message.length()+4));
        System.out.println(' ');
        // print left char
        System.out.print(LEFT_CHAR);
        System.out.print("  " + message + "  ");
        // print right char
        System.out.println(RIGHT_CHAR);
        // print bottom line
        System.out.print(' ');
        System.out.print(repeat_char(LINE_CHAR, message.length()+4));
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
