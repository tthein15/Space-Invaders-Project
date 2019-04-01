import java.awt.Color;
import java.awt.Graphics;

public class Winscreen extends GraphicsObject {

   Color color;


    public Winscreen(int x, int y, Color color) {
        super( x, y);
        this.color = Color.BLACK;
        this.speed_x = 0;
        this.speed_y = 0;
    }

    /* Draw the object
     *
     * This function should never be called directly, but should be overridden
     * by subclasses.
     *
     * @param g The Graphics for the JPanel
     */
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,600,400);
        g.setColor(Color.BLACK);
        g.drawString("YOU'RE A WINNER",200,300 );
    }

    /* Update the object's location based on its speed
     *
     * @param pic_width   The width of the drawing window
     * @param pic_height  The height of the drawing window
     * @param frame       The number of frames since the start of the program
     */
    public void update(int pic_width, int pic_height, int frame) {
        this.x += this.speed_x;
        this.y += this.speed_y;

        super.update(pic_width, pic_height, frame);
    }
}

