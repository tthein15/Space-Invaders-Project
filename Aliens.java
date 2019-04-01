import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Aliens extends GraphicsObject {
    public Aliens (int x, int y) {
        super(x, y);
        this.speed_x = 3;
    }



    @Override
    public void draw(Graphics gr) {
        gr.setColor(Color.green);
        gr.fillRect((int) this.x, (int) this.y, 20, 20);


    }

    public void update(int pic_width, int pic_height, int frame) {
        if (this.x + 20 > pic_width ){
            this.speed_x = -3;
            this.y += 30;
        }
        else if (this.x < 0){
            this.speed_x = 3;
            this.y += 30;
        }

        super.update(pic_width, pic_height, frame);
    }

}

