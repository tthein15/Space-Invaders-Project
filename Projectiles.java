import java.awt.Color;
import java.awt.Graphics;

public class Projectiles extends GraphicsObject {

    Color color;

    public Projectiles(int x, int y, Color color) {
        super(x, y);

        this.color = color;

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)this.x, (int)this.y, 5, 20);


    }


    public boolean valid(){
        if (this.y < -10){
            return true;

        }
        else{
            return false;
        }}
}
