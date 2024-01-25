import stanford.karel.Karel;

public class MidpointFindingKarel extends Karel {

    public void run() {
        midpointFindingKarel();
    }

    void midpointFindingKarel(){
        puttingBeeper();
        turnAround();
        pickingBackBeeper();
    }

    void puttingBeeper(){
        fallBeeper();
        while (frontIsClear()){
            move();
        }
        fallBeeper();
    }

    void pickingBackBeeper(){
       pickAndCheckBeeper();
       while (frontIsClear()){
           move();
           if(beepersPresent()){
               turnAround();
               pickAndCheckBeeper();
           }
       }
    }
    void pickAndCheckBeeper(){
        pickBeeper();
        move();
        fallBeeper();
    }

    void fallBeeper(){
        if(noBeepersPresent()){
            putBeeper();
        }
    }
    void turnAround(){
        turnLeft();
        turnLeft();
    }


}

