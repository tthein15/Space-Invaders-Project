// utility

import java.util.ArrayList;
import java.util.Random;

// graphics
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    Ship hero;

    ArrayList<Aliens> walle;
    ArrayList<Projectiles> shot;
    ArrayList<AlienBombs> killshot;

    Winscreen winner;
    Losingscreen loser;

    int countdown = 0;
    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders() {
        // fix the window size and background color
        this.canvasWidth = 600;
        this.canvasHeight = 400;
        this.backgroundColor = Color.BLACK;
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);

        this.hero = new Ship(250, 360, 20, 20, 5, 5, Color.WHITE);

        this.shot = new ArrayList<Projectiles>();

        this.killshot = new ArrayList<AlienBombs>();

        this.winner = new Winscreen(0,0,Color.BLACK);

        this.loser = new Losingscreen(0,0,Color.RED);



        this.walle = new ArrayList<Aliens>();
        for (int column = 0; column < 6; column++) {
            for (int row = 0; row < 4; row++) {
                this.walle.add(new Aliens(50 * column + 200, 50 * row + 50));
            }
        }
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();

        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        update();
        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);
        // increment the frame counter
        this.frame++;
    }

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);
        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     *
     * @param e  An object describing what key was released
     */
    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     *
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     *
     * @param e  An object describing what key was typed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.x -= 10;
            if (hero.x < 0) {
                hero.x -= 10;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (hero.x < 600) {
                hero.x += 10;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Projectiles bullet = new Projectiles((int) hero.x, 350, Color.RED);
            bullet.speed_y = -10;
            shot.add(bullet);

        }
    }

    /* Update the game objects
     */
    private void update() {
        this.hero.update(this.canvasWidth, this.canvasHeight, this.frame);

        for (Aliens martians : this.walle) {
            martians.update(this.canvasWidth, this.canvasHeight, this.frame);}

            for (int p = 0; p < this.shot.size(); p += 1) {
                Projectiles bullet = this.shot.get(p);
                bullet.update(this.canvasWidth, this.canvasHeight, this.frame);

                if (bullet.valid()) {
                    this.shot.remove(p);}}

                    collision();}

                    private void collision () {

                        for (int b = 0; b < this.shot.size(); b++) {
                            for (int c = 0; c < this.walle.size(); c++) {

                                if (((this.shot.get(b).y >= this.walle.get(c).y)
                                        && (this.shot.get(b).y <= this.walle.get(c).y + 15)
                                        && (this.shot.get(b).x <= this.walle.get(c).x + 15)
                                        && (this.shot.get(b).x >= this.walle.get(c).x))) {
                                    this.walle.remove(c);
                                    this.shot.get(b).y = -20;


                                }
                            }

                                if (countdown == 0) {
                                    Random r = new Random();
                                    int rand = r.nextInt(walle.size());

                                    AlienBombs tnt= new AlienBombs((int) walle.get(rand).x, (int) walle.get(rand).y, Color.RED);
                                    tnt.speed_y = 5;
                                    this.killshot.add(tnt);
                                    int Low = 15;
                                    int High = 45;
                                    countdown = r.nextInt(High - Low) + Low;

                            }

                            countdown--;

                        }
                        for (int d = 0; d < this.killshot.size(); d += 1) {
                            AlienBombs tnt = this.killshot.get(d);
                            tnt.update(this.canvasWidth, this.canvasHeight, this.frame);

                            if (tnt.valid()) {
                                this.shot.remove(d);}}

                                if (hasWonGame()) {
                                    winner.update(this.canvasWidth, this.canvasHeight, this.frame);}

                                    if (hasLostGame()) {
                                        loser.update(this.canvasWidth, this.canvasHeight, this.frame);}







                        }












    /* Check if the player has lost the game
     *
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        for (int s = 0; s < this.killshot.size(); s++)
            for (int c = 0; c < this.walle.size(); c++) {

                if (((this.killshot.get(s).y >= this.hero.y)
                        && (this.killshot.get(s).y <= this.hero.y + 20)
                        && (this.killshot.get(s).x <= this.hero.x + 20)
                        && (this.killshot.get(s).x >= this.hero.x))) {
                    return true;
                } else if (this.walle.get(c).y <= 0) {
                    return true;
                } else if (((this.walle.get(c).y >= this.hero.y)
                        && (this.walle.get(c).y <= this.hero.y + 20)
                        && (this.walle.get(c).x <= this.hero.x + 20)
                        && (this.walle.get(c).x >= this.hero.x))) {
                    return true;
                }


            } return false;
    }



    /* Check if the player has won the game
     *
     * @returns  true if the player has won, false otherwise
     */

            private boolean hasWonGame() {
        int a = 0;
        if (a == walle.size()) {
            return true;}

            return false;
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,600,400);
        this.hero.draw(g);

        int a = 0;
        while (a < walle.size()) {
            walle.get(a).draw(g);
            a += 1;}

            int k = 0;
            while (k < shot.size()) {
                shot.get(k).draw(g);
                k++;}

                    int r = 0;
                    while (r < killshot.size()) {
                    killshot.get(r).draw(g);
                    r++;}


            }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        this.winner.draw(g);
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        this.loser.draw(g);
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
}
