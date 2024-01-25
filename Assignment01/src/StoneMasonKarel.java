import stanford.karel.Karel;

public class StoneMasonKarel extends Karel {

    public void run() {
        stoneMasonKarel();
    }

    void stoneMasonKarel(){
        moveUp();
        moveDown();
        while(frontIsClear()){
            moveFourSteps();
            moveUp();
            moveDown();
        }
    }

    void moveUp(){
        turnLeft();
        puttingBeeper();
        checkFrontClearAndBeeper();
        turnAround();
    }

    void moveDown(){
        while(frontIsClear()){
            move();
        }
        turnLeft();
    }

    void moveFourSteps(){
        for (int i = 0; i <4 ; i++) {
            move();
        }
    }

    void checkFrontClearAndBeeper(){
        while(frontIsClear()){
            move();
            puttingBeeper();
        }
    }

    void puttingBeeper(){
        if(noBeepersPresent()){
            putBeeper();
        }
    }
    void turnAround(){
        turnLeft();
        turnLeft();
    }

}
