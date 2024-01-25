import stanford.karel.Karel;

public class SweepTheStreets extends Karel {

    public void run() {
       sweepStreets();

    }
    void sweepStreets(){
        while(leftIsClear()){
            sweepOneStreet();
            comeBack();
            checkLeftClear();
        }
        sweepOneStreet();
        comeBack();
    }
    void sweepOneStreet(){
        checkFrontClear();
        //at the end
        if(beepersPresent()){
            pickBeeper();
        }
        turnAround();
    }

    void comeBack(){
        while(frontIsClear()){
            move();
        }
        turnAround();
    }

    void checkLeftClear(){
        if(leftIsClear()){
            turnLeft();
            move();
            turnRight();
        }
    }
    void checkFrontClear(){
            while(frontIsClear()){
                if(beepersPresent()){
                    pickBeeper();
                }
                move();
            }
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
