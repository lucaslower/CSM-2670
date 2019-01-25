import java.util.*;

public class Exercise4 {

    // set (x,y) origin
    private static final long X_ORIG = 0;
    private static final long Y_ORIG = 0;

    public static void main(String[] args) {

        // init coords (0,0)
        long x = X_ORIG;
        long y = Y_ORIG;

        // init Random
        Random r = new Random();

        // run first time
        do{
            // print our position
            System.out.println("("+x+","+y+")");
            // find direction
            int dir = r.nextInt(4);
            // switch on dir, 0 --> UP, 1 --> RIGHT, 2 --> DOWN, 3 --> LEFT
            switch(dir){
                case 0:y++;break;
                case 1:x++;break;
                case 2:y--;break;
                case 3:x--;break;
            }
        }
        // keep going until we reach (0,0)
        while(x != X_ORIG || y != Y_ORIG);

        // print final pos
        System.out.println("("+x+","+y+")");
    }
}
