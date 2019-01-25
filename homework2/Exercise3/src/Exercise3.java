import java.util.*;

public class Exercise3 {

    // CHANGE TO TRUE TO SEE RAW OUTPUT IN TABLE (debugging)
    private static final boolean PRINT_INVALIDS = false;

    // init scanner
    private static Scanner sys_in = new Scanner(System.in);
    // prompt counter
    private static int prompt_count = 1;

    // MAIN
    public static void main(String[] args) {

        // print usage info
        System.out.println("CSM 2670 Final Exam Calculator, v1.0");
        System.out.println("Note: averages may be entered with or without decimals.");
        System.out.println();

        // PROMPT: homework average
        double hw_avg = prompt_score("Homework average");

        // PROMPT: exam average
        double exam_avg = prompt_score("Exam average");

        // print final details
        print_table(hw_avg, exam_avg);
    }

    // method to scan/validate next float
    private static double prompt_score(String prompt){
        // init avg and good_input
        double avg = 0.0;
        boolean good_input = false;
        // prompt first time
        do{
            System.out.print(prompt_count+".\t"+prompt+" ==> ");
            try{
                // grab next double (hopefully)
                String line = sys_in.nextLine();
                avg = Double.valueOf(line);
                // validate numerical value
                if(avg < 0 || avg > 100){
                    System.err.println("Error: the number entered must be between 0 and 100.");
                    continue;
                }
                else{
                    good_input = true;
                }
            }
            catch(NumberFormatException ex){
                // print error
                System.err.println("Error: you must enter a number.");
            }
        }
        // continue prompting until we get a valid double
        while(!good_input);
        // increment global prompt count
        prompt_count++;
        // return score as double
        return avg;
    }

    private static String determine_final(double hw_avg, double exam_avg, double lower, double upper){
        double final_needed_lower = (lower-((0.3*hw_avg)+(0.4*exam_avg)))/0.3;
        double final_needed_upper = (upper-((0.3*hw_avg)+(0.4*exam_avg)))/0.3;

        // set values to nearest bound
        // lower
        if(final_needed_lower > 100.0){
            final_needed_lower = 100.0;
        }
        else if(final_needed_lower < 0.0){
            final_needed_lower = 0.0;
        }
        // upper
        if(final_needed_upper > 100.0){
            final_needed_upper = 100.0;
        }
        else if(final_needed_upper < 0.0){
            final_needed_upper = 0.0;
        }

        // check for impossibles
        if(final_needed_upper != final_needed_lower || PRINT_INVALIDS){
            return String.format("%.2f -- %.2f", final_needed_lower, final_needed_upper);
        }
        else{
            return "Impossible";
        }
    }

    private static void print_table(double hw_avg, double exam_avg){
        // print headers
        System.out.println();
        System.out.println("Here's your outcomes . . .");
        System.out.println();
        String headers = String.format("%8s|%24s", "Grade  ", "  Final Exam Grade Range");
        System.out.println(headers);

        // print dividing line
        for(int i = 0; i< headers.length(); i++){
           System.out.print('-');
        }
        System.out.print("\n");

        // format string for row prints
        String format_string = "%8s|%-24s%n";

        // A
        String a_final = determine_final(hw_avg, exam_avg, 90.0, 100.0);
        System.out.printf(format_string, "A  ", "  "+a_final);
        // B
        String b_final = determine_final(hw_avg, exam_avg, 80.0, 89.99);
        System.out.printf(format_string, "B  ", "  "+b_final);
        // C
        String c_final = determine_final(hw_avg, exam_avg, 70.0, 79.99);
        System.out.printf(format_string, "C  ", "  "+c_final);
        // D
        String d_final = determine_final(hw_avg, exam_avg, 55.0, 69.99);
        System.out.printf(format_string, "D  ", "  "+d_final);
        // F
        String f_final = determine_final(hw_avg, exam_avg, 0.0, 54.99);
        System.out.printf(format_string, "F  ", "  "+f_final);
    }
}