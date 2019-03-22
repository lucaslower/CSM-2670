/*
CSM 2670 -- Homework 4
Lucas Lower
03/21/2019

Exercise05Particles.java

Randomly generates moving particles of random diameter and color.
Particles bounce off walls using complementary angles, and live for a fixed time.
*/
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class Exercise05Particles extends JFrame {

    private static final Random rand = new Random();

    // ANIMATION VARIABLES
    // 33 ms for ~30fps
    private static int TIME_STEP = 30;
    // generate a particle around twice a second
    private static double GENERATION_PROB = 0.06;
    // max velocity is 2px/frame
    private static double MAX_VELOCITY = 6.0;
    // particles live for 10 seconds (so about 300 time steps at 30 fps)
    private static int TTL = 300;

    // PARTICLE CLASS
    private static class Particle{

        public int steps;
        private Color c;
        private double x;
        private double y;
        private double x_vel;
        private double y_vel;
        private int size;
        // ball max diameter is 16px
        private static int max_size = 16;

        public Particle(double x, double y){
            this.steps = 0;
            this.c = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            this.x = x;
            this.y = y;
            // get a random size
            this.size = rand.nextInt(max_size);
            // get a random float in the range [-1,1] and multiply by the max velocity
            this.x_vel = (rand.nextFloat()*2 - 1)*MAX_VELOCITY;
            this.y_vel = (rand.nextFloat()*2 - 1)*MAX_VELOCITY;
        }

        public void draw(Graphics g){
            // draw particle
            Graphics2D g2d = (Graphics2D) g;
            Color old = g2d.getColor();
            g2d.setColor(this.c);
            g2d.fill(new Ellipse2D.Double(this.x, this.y, this.size, this.size));
            g2d.setColor(old);
        }

        public void move(int ww, int wh){
            // check if our next move will put us outside the screen
            // if it does in either direction, invert the velocity
            double next_x = this.x + this.x_vel;
            double next_y = this.y + this.y_vel;
            if(next_x < 0 || next_x > ww){
                this.x_vel *= -1;
            }
            if(next_y < 0 || next_y > wh){
                this.y_vel *= -1;
            }
            // move and increment steps alive
            this.x += x_vel;
            this.y += y_vel;
            this.steps++;
        }
    }

    private final DrawPanel main_panel;

    // MAIN PANEL
    private static class DrawPanel extends JPanel {
        // list of particles
        LinkedList<Particle> particles = new LinkedList<>();

        public DrawPanel() {
            setBackground(Color.black);
            // add a single particle
            add_particle();
        }

        public void add_particle(){
            particles.add(new Particle((double) getWidth() / 2, (double) getHeight() / 2));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // draw every particle
            particles.forEach((p) -> p.draw(g));
        }
    }

    // FRAME CONSTRUCTOR
    public Exercise05Particles() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        main_panel = new DrawPanel();
        add(main_panel);
    }

    // MAIN
    public static void main(String[] args) {

        // build app instance
        Exercise05Particles app = new Exercise05Particles();

        // show when ready
        java.awt.EventQueue.invokeLater(() -> app.setVisible(true));

        // loop for movement
        while(true){

            LinkedList<Particle> removes = new LinkedList<>();

            // move all particles, and check for death
            app.main_panel.particles.forEach((p) -> {
                // check for death
                if(p.steps > TTL){
                    removes.add(p);
                }
                else{
                    p.move(app.main_panel.getWidth(), app.main_panel.getHeight());
                }
            });

            // remove particles that need removing
            removes.forEach((p) -> {
                app.main_panel.particles.remove(p);
            });

            // perhaps create a new particle
            if(rand.nextFloat() <= GENERATION_PROB){
                app.main_panel.add_particle();
            }

            // repaint
            app.main_panel.repaint();

            try{
                Thread.sleep(TIME_STEP);
            }
            catch(InterruptedException ex){}
        }
    }
}
