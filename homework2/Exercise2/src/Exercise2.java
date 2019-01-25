/*
CSM 2670 -- Homework 2
Lucas Lower
01/24/2019

Exercise2.java

Prompts the user for their homework average, exam average, and final exam score, then prints their final calculated grade.
Usage: Exercise2 (interactive)
 */
import java.util.*;

public class Exercise2 {

    // init scanner
    private static Scanner sys_in = new Scanner(System.in);
    // prompt counter
    private static int prompt_count = 1;

    // MAIN
    public static void main(String[] args) {

        // print usage info
        System.out.println("CSM 2670 Final Grade Calculator, v1.0");
        System.out.println("Note: averages may be entered with or without decimals.");
        System.out.println();

        // PROMPT: homework average
        double hw_avg = prompt_score("Homework average");

        // PROMPT: exam average
        double exam_avg = prompt_score("Exam average");

        // PROMPT: final exam score
        double final_score = prompt_score("Final exam score");

        // calculate final course score
        double course_score = (hw_avg*0.3)+(exam_avg*0.4)+(final_score*0.3);

        // print final details
        print_info(course_score);
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

    private static void print_info(double grade){
        // determine letter grade
        char letter;
        if(grade < 55.0){
            letter = 'F';
        }
        else if(grade < 70.0){
            letter = 'D';
        }
        else if(grade < 80.0){
            letter = 'C';
        }
        else if(grade < 90.0){
            letter = 'B';
        }
        else{
            letter = 'A';
        }
        // print
        System.out.println();
        System.out.printf("Your final course grade: %C (%.2f)\n", letter, grade);
    }
}
