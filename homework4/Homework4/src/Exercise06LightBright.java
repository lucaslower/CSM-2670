/*
CSM 2670 -- Homework 4
Lucas Lower
03/21/2019

Exercise06LightBright.java

Presents a black screen which is actually a by default 50x50 grid.
When a block within the grid is clicked or dragged over, it is toggled between white and black.
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Exercise06LightBright extends JFrame {

    private static int GRID_SIZE = 50;

    // BLOCK CLASS
    private static class Block{

        public boolean on = false;
        private int col;
        private int row;

        public Block(int col, int row){
            this.col = col;
            this.row = row;
        }

        public void draw(Graphics g, double w, double h){
            // determine position
            double x = w * col;
            double y = h * row;

            // draw block
            Graphics2D g2d = (Graphics2D) g;
            Color old = g2d.getColor();
            g2d.setColor(this.on ? Color.white: Color.black);
            g2d.fill(new Rectangle2D.Double(x, y, w, h));
            g2d.setColor(old);
        }
    }

    // MAIN PANEL
    private static class DrawPanel extends JPanel {

        // 2d Block array
        private Block[][] blocks = new Block[GRID_SIZE][GRID_SIZE];

        // block sizes
        private double CELL_WIDTH;
        private double CELL_HEIGHT;

        // store last block changed by a drag (eliminate flickering)
        private Block PREV_BLOCK = null;

        public DrawPanel() {
            // create all the blocks
            for(int i = 0; i < GRID_SIZE; i++){
                for(int j = 0; j < GRID_SIZE; j++){
                    blocks[i][j] = new Block(i, j);
                }
            }
            // listen for clicks
            addMouseListener(new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e){
                    toggle_block(e);
                }

                @Override
                public void mouseReleased(MouseEvent e){
                    // reset last changed block (on drag) on mouseup
                    PREV_BLOCK = null;
                }
            });
            addMouseMotionListener(new MouseMotionAdapter()
            {

                @Override
                public void mouseDragged(MouseEvent e)
                {
                    toggle_block(e);
                }
            });
        }

        public void toggle_block(MouseEvent e){
            Point click = e.getPoint();
            // determine which block was clicked
            int col = (int) Math.floor(click.x / CELL_WIDTH);
            int row = (int) Math.floor(click.y / CELL_HEIGHT);
            // we check our array bounds because drag events could be delivered
            // from outside of the frame (where no blocks are)
            if(col < GRID_SIZE && row < GRID_SIZE){
                Block clicked = blocks[col][row];
                // toggle block if necessary
                if(PREV_BLOCK == null || PREV_BLOCK != clicked){
                    PREV_BLOCK = clicked;
                    clicked.on = !clicked.on;
                    repaint();
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // determine cell dimensions each repaint
            CELL_WIDTH = (float) getWidth() / GRID_SIZE;
            CELL_HEIGHT = (float) getHeight() / GRID_SIZE;
            // draw every block
            for(Block[] row : blocks){
                for(Block b : row){
                    b.draw(g, CELL_WIDTH, CELL_HEIGHT);
                }
            }
        }
    }

    // FRAME CONSTRUCTOR
    public Exercise06LightBright() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        DrawPanel main_panel = new DrawPanel();
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
        Exercise06LightBright app = new Exercise06LightBright();

        // show when ready
        java.awt.EventQueue.invokeLater(() -> app.setVisible(true));
    }
}
