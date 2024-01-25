import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawCircles extends GraphicsProgram {

    public void init(){
        addMouseListeners();
    }
    public void mouseClicked(MouseEvent e){
        final int SIZE = 50;
        GOval circle = new GOval(SIZE,SIZE);
        circle.setFilled(true);
        circle.setFillColor(Color.GRAY);
        add(circle, e.getX() - SIZE/2.0,e.getY() - SIZE/2.0);
    }
}
