import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public class Exercise03Checkers extends JFrame {

    private final DrawPanel main_panel;

    // MAIN PANEL
    private static class DrawPanel extends JPanel {

        // dimensions of the checker board
        private final int BOARD_SIZE = 8;

        public DrawPanel() {
            setBackground(Color.white);
            setForeground(Color.black);
        }

        private void draw_board(Graphics g, Graphics2D g2d, int board_wh, double board_x, double board_y){
            // determine square size
            double square_wh = (float) board_wh / BOARD_SIZE;
            // loop rows (alternating)
            for(int row = 0; row < BOARD_SIZE; row ++){
                // loops columns (alternating, and starting at 0 or 1)
                for(int col = 0; col < BOARD_SIZE; col++){
                    // determine square's position
                    double x;
                    double y;
                    if(board_x == 0.0){
                        x = col * square_wh;
                        y = board_y + (row * square_wh);
                    }
                    else{
                        x = board_x + (col * square_wh);
                        y = row * square_wh;
                    }

                    // even row
                    if(row % 2 == 0){
                        // even col: draw red square
                        if(col % 2 == 0){
                            g2d.setColor(Color.red);
                            g2d.fill(new Rectangle2D.Double(x, y, square_wh, square_wh));
                        }
                        // odd col: draw checker if within 3 rows of start/end
                        else if(row < 3 || row > BOARD_SIZE - 4){
                            g2d.setColor(Color.white);
                            g2d.fill(new Ellipse2D.Double(x + (square_wh * 0.1), y + (square_wh * 0.1), square_wh * 0.8, square_wh * 0.8));
                        }
                    }
                    // odd row
                    else{
                        // even col: draw red square
                        if(col % 2 == 0){
                            if(row < 3 || row > BOARD_SIZE - 4){
                                g2d.setColor(Color.white);
                                g2d.fill(new Ellipse2D.Double(x + (square_wh * 0.1), y + (square_wh * 0.1), square_wh * 0.8, square_wh * 0.8));
                            }
                        }
                        // odd col: draw checker if within 3 rows of start/end
                        else{
                            g2d.setColor(Color.red);
                            g2d.fill(new Rectangle2D.Double(x, y, square_wh, square_wh));
                        }
                    }

                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // get a g2d object
            Graphics2D g2d = (Graphics2D) g;

            // paint the board background
            // determine bg width/height
            int w = getWidth();
            int h = getHeight();
            int board_wh;
            double x;
            double y;
            if(w <= h){
                board_wh = w;
                x = 0.0;
                y = (h-w) / 2.0;
            }
            else{
                board_wh = h;
                x = (w-h) / 2.0;
                y = 0.0;
            }

            // draw the bound
            g2d.fill(new Rectangle2D.Double(x, y, board_wh, board_wh));

            // draw the grid
            draw_board(g, g2d, board_wh, x, y);
        }
    }

    // FRAME CONSTRUCTOR
    public Exercise03Checkers() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        main_panel = new DrawPanel();
        add(main_panel);
    }

    // MAIN
    public static void main(String[] args) {
        Exercise03Checkers app = new Exercise03Checkers();

        java.awt.EventQueue.invokeLater(() -> {
            app.setVisible(true);
        });
    }
}
