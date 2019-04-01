import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ship extends GraphicsObject {
    Color color;
    int width;
    int height;
    int speed_x;
    int speed_y;

    public Ship (int x, int y, int width, int height, int speed_x, int speed_y, Color color){
        super( x,  y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.speed_x = 0;
        this.speed_y = 0;

    }




    @Override
    public void draw(Graphics gr) {

        gr.setColor(Color.WHITE);
        gr.fillOval((int)this.x,(int)this.y,20,20);



    }

    public void update(int pic_width, int pic_height, int frame) {

        super.update(pic_width, pic_height, frame);
    }

}
