import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Exercise04Grid extends JFrame {

    private static int GRID_SIZE = 50;

    // BLOCK CLASS
    private static class Block{

        private static final Random rand = new Random();

        private Color c;
        private int col;
        private int row;

        public Block(int col, int row){
            this.c = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            this.col = col;
            this.row = row;
        }

        public void draw(Graphics g, int win_w, int win_h){
            // determine dimensions
            double w = ((float) win_w / GRID_SIZE) - 1;
            double h = ((float) win_h / GRID_SIZE) - 1;
            // determine position
            double x = (w * col) + col;
            double y = (h * row) + row;

            // draw block
            Graphics2D g2d = (Graphics2D) g;
            Color old = g2d.getColor();
            g2d.setColor(this.c);
            g2d.fill(new Rectangle2D.Double(x, y, w, h));
            g2d.setColor(old);
        }
    }

    private final DrawPanel main_panel;

    // MAIN PANEL
    private static class DrawPanel extends JPanel {

        // array of blocks
        private LinkedList<Block> blocks = new LinkedList<>();

        public DrawPanel() {
            setBackground(Color.gray);
            setForeground(Color.black);

            // create all the blocks
            for(int i = 0; i < GRID_SIZE; i++){
                for(int j = 0; j < GRID_SIZE; j++){
                    blocks.add(new Block(i, j));
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // draw every block
            this.blocks.forEach((b) -> b.draw(g, getWidth(), getHeight()));
        }
    }

    // FRAME CONSTRUCTOR
    public Exercise04Grid() {
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
            catch(NumberFormatException ex){}
        }

        // build app instance
        Exercise04Grid app = new Exercise04Grid();

        // show when ready
        java.awt.EventQueue.invokeLater(() -> app.setVisible(true));
    }
}
