/*
CSM 2670 -- Homework 4
Lucas Lower
03/21/2019

Exercise02Pyramid.java

Draws a blue rectangle 100% of the window height and 80% of the width.
Inside this rectangle, a pyramid of NUM_ROWS (member of DrawPanel) height and width is drawn.
"Blocks" of the pyramid are scaled to fit accordingly, and are colored orange with red borders.
*/
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Exercise02Pyramid extends JFrame {

    private final DrawPanel main_panel;

    // MAIN PANEL
    private static class DrawPanel extends JPanel
    {

        // this is the number of rows in the pyramid,
        // and also the number of bricks in the bottom row
        private final int NUM_ROWS = 10;

        public DrawPanel()
        {
            setBackground(Color.white);
            setForeground(Color.blue);
        }

        private void paint_pyramid(Graphics g, Graphics2D g2d){
            // determine brick width and height
            int w = getWidth();
            int h = getHeight();
            double brick_height = h / (float) NUM_ROWS;
            double brick_width = (w * 0.8) / NUM_ROWS;
            // get centered rectangle offset
            double cont_offset = w * 0.1;
            // loop through each row, then each brick in the row, starting at the bottom
            for(int i = 0; i < NUM_ROWS; i++){
                // calculate row y
                double y = brick_height * (NUM_ROWS - (i + 1 ));
                // calculate row offset
                double row_offset = i * (brick_width/2);
                for(int j = 0; j < NUM_ROWS - i; j++){
                    // calculate brick x
                    double x = cont_offset + row_offset + (j * brick_width);
                    // draw outer rectangle (red border)
                    g2d.setColor(Color.red);
                    g2d.fill(new Rectangle2D.Double(x, y, brick_width, brick_height));
                    // draw inner (orange fill)
                    g2d.setColor(Color.orange);
                    g2d.fill(new Rectangle2D.Double(x+1, y+1, brick_width-2, brick_height-2));
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // get a g2d object
            Graphics2D g2d = (Graphics2D) g;

            // paint the 80% width rectangle (using g2d because percentages might not be int)
            setForeground(Color.blue);
            int w = getWidth();
            g2d.fill(new Rectangle2D.Double(w * 0.1, 0, w * 0.8, getHeight()));

            // paint the blocks
            paint_pyramid(g, g2d);
        }
    }

    // FRAME CONSTRUCTOR
    public Exercise02Pyramid()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        main_panel = new DrawPanel();
        add(main_panel);
    }

    // MAIN
    public static void main(String[] args) {
	    Exercise02Pyramid app = new Exercise02Pyramid();

        java.awt.EventQueue.invokeLater(() ->
        {
            app.setVisible(true);
        });
    }
}
