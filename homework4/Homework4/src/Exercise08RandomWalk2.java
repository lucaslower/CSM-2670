/*
CSM 2670 -- Homework 4
Lucas Lower
03/21/2019

Exercise08RandomWalk2.java

Draws a by default 50x50 grid with cells outlined in grey.
A red block takes a random walk through the grid but does not leave the window bounds.
While walking, the cells under the red block are turned white.
The moving block is not allowed to move back over white-marked cells.
*/
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Exercise08RandomWalk2 extends JFrame {

    private static int GRID_SIZE = 50;
    private static int TIME_STEP = 200;
    private static final Random rand = new Random();

    // BLOCK CLASS
    private static class Block{

        public Color c;
        private int col;
        private int row;

        public Block(Color c, int col, int row){
            this.col = col;
            this.row = row;
            this.c = c;
        }

        public void draw(Graphics g, double w, double h){
            // determine position
            double x = (w * col) + col;
            double y = (h * row) + row;

            // fix edge borders
            if(this.col == GRID_SIZE - 1){
                w++;
            }
            if(this.row == GRID_SIZE - 1){
                h++;
            }

            // draw block
            Graphics2D g2d = (Graphics2D) g;
            Color old = g2d.getColor();
            g2d.setColor(this.c);
            g2d.fill(new Rectangle2D.Double(x, y, w, h));
            g2d.setColor(old);
        }

        public boolean move(Block[][] blocks){
            // determine valid moves and add them to a list to be randomly selected from
            // a move is valid if the block exists and is not white
            // if no valid moves, return false
            ArrayList<Character> moves = new ArrayList<>();
            if(this.col > 0 && blocks[this.col - 1][this.row].c != Color.white){
                moves.add('W');
            }
            if(this.col < GRID_SIZE - 1 && blocks[this.col + 1][this.row].c != Color.white){
                moves.add('E');
            }
            if(this.row > 0 && blocks[this.col][this.row - 1].c != Color.white){
                moves.add('N');
            }
            if(this.row < GRID_SIZE - 1 && blocks[this.col][this.row + 1].c != Color.white){
                moves.add('S');
            }
            if(moves.size() > 0){
                // pick a random direction
                char dir = moves.get(rand.nextInt(moves.size()));
                // move there
                switch(dir){
                    case 'W':
                        this.col--;
                        break;
                    case 'E':
                        this.col++;
                        break;
                    case 'N':
                        this.row--;
                        break;
                    case 'S':
                        this.row++;
                        break;
                }
                return true;
            }
            else{
                return false;
            }
        }
    }

    private final DrawPanel main_panel;

    // MAIN PANEL
    private static class DrawPanel extends JPanel {

        // moving block
        private Block mover;

        // 2d Block array
        private Block[][] blocks = new Block[GRID_SIZE][GRID_SIZE];

        // block sizes
        private double CELL_WIDTH;
        private double CELL_HEIGHT;

        public DrawPanel() {
            setBackground(Color.gray);
            // create grid blocks
            for(int i = 0; i < GRID_SIZE; i++){
                for(int j = 0; j < GRID_SIZE; j++){
                    blocks[i][j] = new Block(Color.black, i, j);
                }
            }
            // create moving block
            mover = new Block(Color.red, GRID_SIZE / 2, GRID_SIZE / 2);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // determine cell dimensions each repaint
            CELL_WIDTH = (float) getWidth() / GRID_SIZE;
            CELL_WIDTH--;
            CELL_HEIGHT = (float) getHeight() / GRID_SIZE;
            CELL_HEIGHT--;
            // draw every block
            for(Block[] row : blocks){
                for(Block b : row){
                    // check if we are under the mover, if so change white
                    if(b.col == mover.col && b.row == mover.row){
                        b.c = Color.white;
                    }
                    b.draw(g, CELL_WIDTH, CELL_HEIGHT);
                }
            }
            // draw mover
            mover.draw(g, CELL_WIDTH, CELL_HEIGHT);
        }
    }

    // FRAME CONSTRUCTOR
    public Exercise08RandomWalk2() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        main_panel = new DrawPanel();
        add(main_panel);
    }

    // MAIN
    public static void main(String[] args) {

        if(args.length > 0){
            // get grid size or keep default
            try{
                GRID_SIZE = Integer.valueOf(args[0]);
            }
            catch(NumberFormatException ex){
                System.err.println("Grid size supplied invalid... using default (50x50).");
            }
        }

        // build app instance
        Exercise08RandomWalk2 app = new Exercise08RandomWalk2();

        // show when ready
        java.awt.EventQueue.invokeLater(() -> app.setVisible(true));

        // loop for movement
        while(true){
            boolean moved = app.main_panel.mover.move(app.main_panel.blocks);
            app.main_panel.repaint();
            if(moved){
                // wait a step
                try{
                    Thread.sleep(TIME_STEP);
                }
                catch(InterruptedException ex){}
            }
            else{
                // wait 10 steps then reset
                try{
                    Thread.sleep(10*TIME_STEP);
                }
                catch(InterruptedException ex){}
                // print status
                System.out.println("Resetting...");
                // RESET
                // set all blocks to white
                for(int i = 0; i < GRID_SIZE; i++){
                    for(int j = 0; j < GRID_SIZE; j++){
                        app.main_panel.blocks[i][j].c = Color.black;
                    }
                }
                // rebuild mover block
                app.main_panel.mover.c = Color.red;
                app.main_panel.mover.row = GRID_SIZE / 2;
                app.main_panel.mover.col = GRID_SIZE / 2;
            }

        }
    }
}
