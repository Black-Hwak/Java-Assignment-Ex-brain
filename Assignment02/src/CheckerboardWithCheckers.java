import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class CheckerboardWithCheckers extends GraphicsProgram {
    public void run() {

        final int BOARD_SIZE = 8;
        final int SIZE = getHeight() / BOARD_SIZE;
        int margin = 5;

        for (int i = 0; i <BOARD_SIZE ; i++) {
            int startPointRow = (getWidth() - SIZE * BOARD_SIZE)/2;
            int startPointCol = i * SIZE;
            for (int j = 0; j < BOARD_SIZE; j++) {
                GRect rect = new GRect(startPointRow,startPointCol ,SIZE,SIZE);
                boolean isFilled = (i+j)%2==1;
                rect.setFilled(isFilled);
                rect.setFillColor(Color.GRAY);
                add(rect);

                //Filling color
                if(isFilled){
                    GOval oval = new GOval(startPointRow + margin, startPointCol + margin,
                            SIZE-2 * margin,SIZE-2 * margin);
                    oval.setFilled(true);
                    if(i < BOARD_SIZE/2-1){
                        oval.setFillColor(Color.RED);
                        add(oval);
                    }else if(i > BOARD_SIZE/2){
                        oval.setFillColor(Color.BLACK);
                        add(oval);
                    }
                }
                startPointRow+=SIZE;
            }
        }
    }

}
