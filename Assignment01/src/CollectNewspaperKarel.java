import stanford.karel.Karel;

public class CollectNewspaperKarel extends Karel {

    public void run() {
        collectNewspaper();
    }

    void collectNewspaper(){
        goToNewspaper();
        pickBeeper();
        turnAround();
        comeBack();
    }

    void goToNewspaper(){
            checkFrontClear();
            while(leftIsBlocked()){
                move();
            }
            turnLeft();
            move();
    }

    void comeBack(){
       checkFrontClear();
       checkFrontClear();
    }

    void checkFrontClear(){
        while(frontIsClear()){
            move();
        }
        turnRight();
    }
    void turnAround(){
        turnLeft();
        turnLeft();
    }

    void turnRight(){
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
