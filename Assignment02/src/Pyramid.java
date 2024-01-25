import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class Pyramid extends GraphicsProgram {
    public void run(){
        final int BRICK_WIDTH = 46;
        final int BRICK_HEIGHT = 20;
        final int  BRICK_IN_BASE = 14;

        int baseHeight = getHeight()-BRICK_HEIGHT;
        int brickCount = BRICK_IN_BASE;

        //Starting from Bottom
        for (int i = BRICK_IN_BASE; i >=1 ; i--) {
            int baseWidth = (getWidth() - BRICK_WIDTH*i)/2;
            for (int j = brickCount; j >=1; j--) {
                GRect rect = new GRect(baseWidth,baseHeight, BRICK_WIDTH, BRICK_HEIGHT);
                boolean isFiled = brickCount%2==1;
                rect.setFilled(isFiled);
                rect.setFillColor(Color.LIGHT_GRAY);
                add(rect);
                baseWidth+=BRICK_WIDTH;
            }
            brickCount--;
            baseHeight-=BRICK_HEIGHT;
        }
    }
}
