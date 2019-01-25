/*
CSM 2670 -- Homework 2
Lucas Lower
01/24/2019

Exercise5.java

Prints a representation of pascal's triangle.
Usage: Exercise5 [NUMBER OF ROWS (int)] [MODULUS (int)]
 */
public class Exercise5 {

    public static void main(String[] args) {
        if(args.length < 3 && args.length > 0){
            // get number of rows
            int num_rows = 0;
            try{
                num_rows = Integer.valueOf(args[0]);
            }
            catch(NumberFormatException ex){
                System.err.println("Error: invalid argument. Usage: Exercise5 [NUMBER OF ROWS (int)] [MODULUS (int)]");
                System.exit(-1);
            }

            // get modulus
            int modulus = 0;
            if(args.length == 2){
                try{
                    modulus = Integer.valueOf(args[1]);
                }
                catch(NumberFormatException ex){
                    System.err.println("Error: invalid argument. Usage: Exercise5 [NUMBER OF ROWS (int)] [MODULUS (int)]");
                    System.exit(-1);
                }
            }

            // determine max field length
            long[] max_coefficients = binomial(num_rows-1);
            long max_val = 0;
            for(long val : max_coefficients){
                if(val > max_val){
                    max_val = val;
                }
            }
            int field_length = String.valueOf(max_val).length()+1;
            if(field_length % 2 == 1){
                field_length++;
            }

            // build triangle
            for(int i = 0; i < num_rows; i++){
                // print row offset
                if(num_rows % 2 == 1){
                    num_rows --;
                }
                int offset = ((num_rows-i)/2)*field_length;
                if(i % 2 == 1){
                    offset += field_length/2;
                }
                for(int j = 0; j < offset; j++){
                    System.out.print(" ");
                }
                // print row terms
                long[] coefficients = binomial(i);
                for(long coeff : coefficients){
                    // compute mod if arg is set
                    if(args.length == 2){
                        System.out.printf("%"+field_length+"d", coeff % modulus);
                    }
                    // no mod, print normal
                    else{
                        System.out.printf("%"+field_length+"d", coeff);
                    }
                }
                // newline
                System.out.println();
            }
        }
        // wrong number of args
        else{
            System.err.println("Error: incorrect usage. Usage: Exercise5 [NUMBER OF ROWS (int)] [MODULUS (int)]");
        }

    }

    // the typical recursive factorial function
    private static long factorial(int n){
        if(n == 0){
            return 1;
        }
        else{
            return n*factorial(n-1);
        }
    }

    // n choose r function, using factorial
    private static long choose(int n, int r){
        return factorial(n)/(factorial(r)*factorial(n-r));
    }

    // binomial coefficient function (returns array of coefficients)
    private static long[] binomial(int exp){
        // init return array
        long[] coefficients = new long[exp+1];
        // loop through terms
        for(int i = 0; i < exp+1; i++){
            coefficients[i] = choose(exp, exp-(exp-i));
        }
        // return
        return coefficients;
    }
}
