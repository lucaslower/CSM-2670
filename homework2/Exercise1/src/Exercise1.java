/*
CSM 2670 -- Homework 2
Lucas Lower
01/24/2019

Exercise1.java

Prints the sum of the first n fractions of the form 1/n
Usage: Exercise1 [NUMBER OF TERMS (integer)]
 */
public class Exercise1 {

    public static void main(String[] args) {
        // get number of terms
        int num_terms = get_arg(args);
        // calculate sum
        double sum = 0.0;
        for(int i = 1; i <= num_terms; i++){
            sum += (double) 1/i;
        }
        // print answer
        System.out.println("Sum: "+sum);
    }

    private static int get_arg(String[] args){
        // init return
        int num_terms = 0;
        // check that we have an arg
        if(args.length == 0 || args.length > 1){
            System.err.println("Error: incorrect usage. Usage: Exercise1 [NUMBER OF TERMS (integer)]");
            System.exit(-1);
        }
        else{
            // get number of terms
            try{
                num_terms = Integer.valueOf(args[0]);
            }
            catch(NumberFormatException ex){
                System.err.println("Error: invalid argument. Usage: Exercise1 [NUMBER OF TERMS (integer)]");
                System.exit(-1);
            }
        }
        return num_terms;
    }
}
