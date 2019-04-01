import java.awt.Color;
import java.awt.Graphics;

public class AlienBombs extends GraphicsObject {

    Color color;

    public AlienBombs(int x, int y, Color color) {
        super(x, y);

        this.color = color;

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)this.x, (int)this.y, 10, 10);


    }


    public boolean valid(){
        if (this.y < -10){
            return true;

        }
        else{
            return false;
        }}
}
