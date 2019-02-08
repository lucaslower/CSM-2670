import java.util.*;

public class Exercise2 {

    private static final Scanner INPUT = new Scanner(System.in);
    private static int COMPUTER_SCORE = 0;
    private static int PLAYER_SCORE = 0;

    public static void main(String[] args) {
        // print welcome
        System.out.println("Welcome to RPSLS (rock, paper, scissors, lizard, spock)!");
        System.out.println();
        // init match count
        int match_counter = 0;
	    // main application loop
        while(true){
            play_match();
            match_counter++;
            // continue?
            String cont = get_input("Do you want to keep playing? (Y/N)");
            if(cont.toUpperCase().equals("N")){
                break;
            }
            System.out.println();
        }
        // print final stats
        System.out.println("FINAL GAME STATS:");
        System.out.println("Games played:\t"+match_counter);
        System.out.println("\tComputer Score:\t"+COMPUTER_SCORE);
        System.out.println("\tYour Score:\t"+PLAYER_SCORE);
        if(COMPUTER_SCORE > PLAYER_SCORE){
            System.out.println("Computer wins!");
        }
        else if(COMPUTER_SCORE < PLAYER_SCORE){
            System.out.println("You win!");
        }
        else{
            System.out.println("It's a tie!");
        }
    }

    private static void play_match(){
        // print game starter
        System.out.println("Let's play!");
        System.out.println();
        // determine user move
        String player_move = "";
        // create valid moves list
        ArrayList<String> valid_moves = new ArrayList<>();
            valid_moves.add("rock");
            valid_moves.add("paper");
            valid_moves.add("scissors");
            valid_moves.add("lizard");
            valid_moves.add("spock");
        // keep asking until we get a good move
        while(!valid_moves.contains(player_move)){
            player_move = get_input("Enter a move from the following: rock, paper, scissors, lizard, spock");
        }
        // generate computer move
        String computer_move = generate_move();
        // determine winner
        int winner = determine_winner(player_move, computer_move);
        // print results
        System.out.println("MATCH OUTCOME:");
        System.out.println("\tYou chose:\t"+player_move);
        System.out.println("\tComputer chose:\t"+computer_move);
        switch(winner){
            case 0:
                System.out.println("\tComputer wins!");
                COMPUTER_SCORE++;
                break;
            case 1:
                System.out.println("\tYou win!");
                PLAYER_SCORE++;
                break;
            case 2:
                System.out.println("\tIt's a tie!");
                break;
        }
    }

    private static int determine_winner(String p, String c){
        // I would use a switch here, but String is an object, of course
        if(c.equals(p)){
            return 2;
        }
        if(c.equals("rock") && (p.equals("paper") || p.equals("spock"))){
            return 1;
        }
        else if(c.equals("paper") && (p.equals("scissors") || p.equals("lizard"))){
            return 1;
        }
        else if(c.equals("scissors") && (p.equals("rock") || p.equals("spock"))){
            return 1;
        }
        else if(c.equals("lizard") && (p.equals("scissors") || p.equals("rock"))){
            return 1;
        }
        else if(c.equals("spock") && (p.equals("paper") || p.equals("lizard"))){
            return 1;
        }
        else{
            return 0;
        }
    }

    private static String get_input(String prompt){
        System.out.println(prompt);
        System.out.print(">  ");
        String input = "";
        if(INPUT.hasNext()){
            input = INPUT.next();
        }
        return input;
    }

    private static String generate_move(){
        int move_num = (new Random()).nextInt(6);
        String[] moves = {"rock", "paper", "scissors", "lizard", "spock"};
        return moves[move_num];
    }
}
